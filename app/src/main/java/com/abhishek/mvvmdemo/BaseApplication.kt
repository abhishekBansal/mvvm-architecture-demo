package com.abhishek.mvvmdemo

import android.app.Application
import com.abhishek.mvvmdemo.di.appModule
import com.abhishek.mvvmdemo.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            // load properties from assets/koin.properties file
            androidFileProperties()
            modules(listOf(viewModelsModule, appModule))
        }
    }
}
