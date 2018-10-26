package com.abhishek.mvvmdemo.model


data class LoginResponse(
    val accessToken: String?,
    val username: String?,
    val userId: String?,
    val error: Error?
)

data class Error(
    val message: String,
    val code: Int
)