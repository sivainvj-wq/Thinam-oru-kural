package com.thirukkural.app.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.thirukkural.app.data.db.KuralDatabase
import com.thirukkural.app.utils.PreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val prefs = PreferencesManager(context)
        if (!prefs.isNotificationEnabled()) return

        CoroutineScope(Dispatchers.IO).launch {
            val dao = KuralDatabase.getDatabase(context).kuralDao()
            val todayKuralNumber = prefs.getTodayKuralNumber()
            val kural = dao.getKuralByNumber(todayKuralNumber)

            kural?.let {
                val (l1, l2) = it.getFormattedLines()
                NotificationHelper.showKuralNotification(
                    context,
                    it.number,
                    l1,
                    l2
                )
                // Advance to next kural for tomorrow
                prefs.advanceKural()
            }
        }
    }
}
