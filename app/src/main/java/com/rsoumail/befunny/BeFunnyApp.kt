package com.rsoumail.befunny

import android.app.Application
import com.rsoumail.befunny.di.appModule
import com.rsoumail.befunny.feature.funnyreal.di.funnyRealModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class BeFunnyApp : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BeFunnyApp)
            workManagerFactory()
            modules(
                appModule,
                funnyRealModule
            )
        }
    }
}