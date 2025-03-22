package com.rsoumail.befunny

import android.app.Application
import com.rsoumail.befunny.feature.funnyreal.di.funnyRealModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BeFunnyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BeFunnyApp)
            modules(
                funnyRealModule
            )
        }
    }
}