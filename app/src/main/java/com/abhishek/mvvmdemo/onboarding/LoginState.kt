package com.abhishek.mvvmdemo.onboarding

import androidx.annotation.StringRes

sealed class LoginState {
    abstract val isLoading: Boolean
}

data class IdleState (
    override val isLoading: Boolean = false
): LoginState()

data class LoadingState(
    override val isLoading: Boolean = true
    ) : LoginState()

data class LoginSuccessState(
    override val isLoading: Boolean = false
) : LoginState()

data class LoginErrorState(
    override val isLoading: Boolean = true,
    @StringRes val error: Int,
    val errorType: ErrorType
) : LoginState()


enum class ErrorType {
    EMAIL, PASSWORD, EMAIL_AND_PASSWORD
}