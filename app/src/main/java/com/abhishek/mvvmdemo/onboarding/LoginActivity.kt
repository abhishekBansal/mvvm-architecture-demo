package com.abhishek.mvvmdemo.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.abhishek.mvvmdemo.HomeActivity
import com.abhishek.mvvmdemo.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), Observer<LoginState> {

    private val loginViewModel: LoginViewModel by viewModel()

    private val disposableBag: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener {
            this.login()
        }

        loginViewModel.mutableLiveData.observe(this, this)
    }

    override fun onChanged(state: LoginState?) {
        when (state) {
            is LoginSuccessState -> {
                goToHome()
            }

            is LoginErrorState -> {
                hideProgress()
                showErrors(state.error, state.errorType)
            }

            is LoadingState -> {
                emailTil.isErrorEnabled = false
                passwordTil.isErrorEnabled = false
                showProgress()
            }

            is IdleState -> {
                hideProgress()
            }
        }
    }

    private fun showErrors(error: Int, errorType: ErrorType) {
        val errorString = getString(error)
        when (errorType) {
            ErrorType.EMAIL -> emailTil.error = errorString
            ErrorType.PASSWORD -> {
                passwordTil.error = errorString
                emailTil.isErrorEnabled = false
            }
            ErrorType.EMAIL_AND_PASSWORD -> {
                emailTil.error = errorString
                passwordTil.error = errorString
            }
        }
    }

    private fun login() {
        val email: String = emailEt.text.toString()
        val password: String = passwordEt.text.toString()
        loginViewModel.login(email, password)
    }

    private fun showProgress() {
        loginBtn.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        loginBtn.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    private fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.dispose()
        loginViewModel.destroy()
    }
}