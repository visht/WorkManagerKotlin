package com.chat.me.workmanagerkotlin

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.support.v4.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by vishal on 20/09/19.
 */
class NotificationWoker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        triggerNotification()
        return Result.success();
    }

    fun triggerNotification() {
        var notiMgr: NotificationManager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notChannel: NotificationChannel = NotificationChannel("workvisht", "workvisht", NotificationManager.IMPORTANCE_DEFAULT)
            notiMgr.createNotificationChannel(notChannel)
        }

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, "workvisht")

        builder.setContentTitle("Notification from Visht")
        builder.setContentText("Wow you are consuming worker")
        builder.setSmallIcon(R.mipmap.ic_launcher)

        var notification: Notification = builder.build()

        notiMgr.notify(12, notification)

    }
}