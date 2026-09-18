package com.jumparoundcreations.toolbox

import android.app.Application
import com.jumparoundcreations.toolbox.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
        }
    }
}