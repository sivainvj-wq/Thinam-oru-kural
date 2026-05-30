package com.thirukkural.app.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.thirukkural.app.utils.PreferencesManager
import com.thirukkural.app.utils.NotificationScheduler

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED ||
            intent.action == Intent.ACTION_MY_PACKAGE_REPLACED) {
            val prefs = PreferencesManager(context)
            if (prefs.isNotificationEnabled()) {
                NotificationScheduler.scheduleDaily(context)
            }
        }
    }
}
