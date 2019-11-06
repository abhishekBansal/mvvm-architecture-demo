package com.abhishek.mvvmdemo.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishek.mvvmdemo.R
import com.abhishek.mvvmdemo.api.MockApiService
import com.abhishek.mvvmdemo.model.Error
import com.abhishek.mvvmdemo.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

open class LoginViewModel(private val apiService: MockApiService) : ViewModel() {
    private val disposableBag = CompositeDisposable()
    val mutableLiveData = MutableLiveData<LoginState>()

    fun login(email: String, password: String) {
        val user = User(email, password)
        val validationResult = user.validate()

        if (validationResult == User.ValidationResult.NO_ERROR) {
            // correct input go ahead and make api call
            mutableLiveData.value = LoadingState()
            val apiResponse = apiService.login(User(email, password)).cache()
            val errorResponse = apiResponse.filter { response -> response.error != null }
            disposableBag.add(
                errorResponse
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        apiError(response.error)
                    }, Timber::e)
            )

            disposableBag.add(
                apiResponse
                    .filter { response -> response.error == null }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        // store access token here
                        mutableLiveData.value = LoginSuccessState()
                    }, Timber::e)
            )
        } else {
            validationError(validationResult)
        }

        return
    }

    private fun apiError(error: Error?) {
        when (error?.code) {
            11 ->
                mutableLiveData.value = LoginErrorState(
                    error = R.string.invalid_username_or_password,
                    errorType = ErrorType.EMAIL_AND_PASSWORD
                )
        }
    }

    private fun validationError(validationResult: User.ValidationResult) {
        when (validationResult) {
            User.ValidationResult.INVALID_EMAIL ->
                mutableLiveData.value = LoginErrorState(error = R.string.invalid_email, errorType = ErrorType.EMAIL)
            User.ValidationResult.EMPTY_EMAIL ->
                mutableLiveData.value = LoginErrorState(error = R.string.empty_email, errorType = ErrorType.EMAIL)
            User.ValidationResult.EMPTY_PASSWORD ->
                mutableLiveData.value = LoginErrorState(error = R.string.empty_password, errorType = ErrorType.PASSWORD)
            User.ValidationResult.NO_ERROR ->
                mutableLiveData.value = IdleState()
        }
    }

    fun destroy() {
        disposableBag.dispose()
    }
}