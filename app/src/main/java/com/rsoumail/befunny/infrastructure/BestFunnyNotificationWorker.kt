package com.rsoumail.befunny.infrastructure

import android.content.Context
import androidx.work.WorkerParameters
import com.rsoumail.befunny.R

class BestFunnyNotificationWorker (
    context: Context,
    workerParams: WorkerParameters,
    override val uri: String = "app://best-funny",
    override val title: String = context.getString(R.string.best_funny_notification_title),
    override val description: String = context.getString(R.string.best_funny_notification_description)
) : NotificationWorker(context, workerParams)