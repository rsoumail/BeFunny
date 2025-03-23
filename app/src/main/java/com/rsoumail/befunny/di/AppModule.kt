package com.rsoumail.befunny.di

import androidx.work.WorkManager
import com.rsoumail.befunny.infrastructure.BestFunnyNotificationWorker
import com.rsoumail.befunny.infrastructure.NewFunnyNotificationWorker
import com.rsoumail.befunny.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.workmanager.dsl.worker
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel {
        MainViewModel(
            workManager = get()
        )
    }

    worker {
        NewFunnyNotificationWorker(
            androidApplication(),
            workerParams = get()
        )
    }

    worker {
        BestFunnyNotificationWorker(
            androidApplication(),
            workerParams = get()
        )
    }
    single { WorkManager.getInstance(androidApplication()) }
}