package com.abhishek.mvvmdemo

import android.app.Application
import com.abhishek.mvvmdemo.di.appModule
import com.abhishek.mvvmdemo.di.viewModelsModule
import org.koin.android.ext.android.startKoin

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(viewModelsModule, appModule))
    }
}
