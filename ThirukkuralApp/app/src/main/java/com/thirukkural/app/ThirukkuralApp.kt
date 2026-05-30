package com.thirukkural.app

import android.app.Application
import com.thirukkural.app.notifications.NotificationHelper

class ThirukkuralApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(this)
    }
}
