package com.rsoumail.befunny.ui

import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.rsoumail.befunny.infrastructure.NewFunnyNotificationWorker
import java.util.concurrent.TimeUnit

const val NOTIFICATION_SIMULATOR_WORKER = "notification_simulator"

class MainViewModel(
    workManager: WorkManager
) : ViewModel() {

    private val periodicWorkRequest = PeriodicWorkRequest.Builder(
        NewFunnyNotificationWorker::class.java,
        15, TimeUnit.MINUTES
    )
        .setConstraints(Constraints.Builder()
            .build())
        .build()

    init {
        workManager.enqueueUniquePeriodicWork(
            NOTIFICATION_SIMULATOR_WORKER,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }
}