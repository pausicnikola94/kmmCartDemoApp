package com.example.kmmcartdemoapp.android

import android.app.Application
import com.example.kmmcartdemoapp.android.di.androidPresentationModule
import com.example.kmmcartdemoapp.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class KmmCartDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@KmmCartDemoApplication)
            modules(appModule, androidPresentationModule)
        }
    }
}