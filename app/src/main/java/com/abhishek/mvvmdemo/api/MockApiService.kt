package com.abhishek.mvvmdemo.api

import com.abhishek.mvvmdemo.model.Error
import com.abhishek.mvvmdemo.model.LoginResponse
import com.abhishek.mvvmdemo.model.User
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class MockApiService {
    fun login(user: User): Observable<LoginResponse> {
        val result = user.validate()
        return if (result == User.ValidationResult.NO_ERROR) {
            if (user.username == "abhishek@gmail.com" && user.password == "1234") {
                Observable.just(LoginResponse("accessToken", "Abhishek", "uuid", null))
                    .delay(3, TimeUnit.SECONDS)
            } else {
                Observable.just(LoginResponse(null, null, null, Error("Invalid Username or Password", 11)))
                    .delay(3, TimeUnit.SECONDS)
            }
        } else {
            Observable.just(LoginResponse(null, null, null, Error(result.toString(), result.ordinal)))
                .delay(3, TimeUnit.SECONDS)
        }
    }
}