package com.abhishek.mvvmdemo.di

import com.abhishek.mvvmdemo.onboarding.LoginViewModel
import com.abhishek.mvvmdemo.api.ApiModule
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelsModule = module {
    viewModel { LoginViewModel(get()) }
}

val appModule = module {
    single { ApiModule.providesApiService() }
}