package com.abhishek.mvvmdemo.api

/**
 * Created by abhishek
 * on 05/04/16.
 */
class ApiModule {

    companion object {
        fun providesApiService(): MockApiService {
            return MockApiService()
        }
    }
}