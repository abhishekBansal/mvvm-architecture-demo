package com.abhishek.mvvmdemo.model

import android.util.Patterns


data class User(
    val username: String,
    val password: String
) {

    enum class ValidationResult {
        INVALID_EMAIL,
        EMPTY_EMAIL,
        EMPTY_PASSWORD,
        NO_ERROR
    }

    fun validate(): ValidationResult {
        return when {
            username.isBlank() -> ValidationResult.EMPTY_EMAIL
            !Patterns.EMAIL_ADDRESS.matcher(username).matches() -> ValidationResult.INVALID_EMAIL
            password.isBlank() -> ValidationResult.EMPTY_PASSWORD
            else -> ValidationResult.NO_ERROR
        }
    }
}