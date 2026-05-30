package com.thirukkural.app.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.thirukkural.app.R
import com.thirukkural.app.ui.MainActivity
import com.thirukkural.app.utils.PreferencesManager

object NotificationHelper {

    const val CHANNEL_ID = "thirukkural_daily"
    const val CHANNEL_NAME = "Daily Kural"
    const val NOTIFICATION_ID = 1001
    const val EXTRA_KURAL_NUMBER = "kural_number"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Daily Thirukkural notification"
                enableVibration(true)
            }
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    fun showKuralNotification(context: Context, kuralNumber: Int, kuralLine1: String, kuralLine2: String) {
        val prefs = PreferencesManager(context)
        val language = prefs.getLanguage()

        val title = if (language == "ta") "இன்றைய திருக்குறள் #$kuralNumber" else "Today's Thirukkural #$kuralNumber"
        val content = "$kuralLine1\n$kuralLine2"

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(EXTRA_KURAL_NUMBER, kuralNumber)
        }

        val pendingIntent = PendingIntent.getActivity(
            context, kuralNumber,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(content)
            .setStyle(NotificationCompat.BigTextStyle().bigText(content))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }
}
