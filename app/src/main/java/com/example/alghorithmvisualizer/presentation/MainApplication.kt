package com.example.alghorithmvisualizer.presentation

import android.app.Application
import com.example.alghorithmvisualizer.di.AppModule
import com.example.alghorithmvisualizer.di.AppModuleImpl

class MainApplication : Application() {
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}