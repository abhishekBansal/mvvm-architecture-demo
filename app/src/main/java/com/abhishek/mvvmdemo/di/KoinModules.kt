package com.abhishek.mvvmdemo.di

import com.abhishek.mvvmdemo.onboarding.LoginViewModel
import com.abhishek.mvvmdemo.api.ApiModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { LoginViewModel(get()) }
}

val appModule = module {
    single { ApiModule.providesApiService() }
}