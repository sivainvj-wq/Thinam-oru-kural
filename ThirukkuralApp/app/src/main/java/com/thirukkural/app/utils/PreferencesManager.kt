package com.thirukkural.app.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "thirukkural_prefs"
        private const val KEY_LANGUAGE = "language"
        private const val KEY_NOTIFICATION_ENABLED = "notification_enabled"
        private const val KEY_NOTIFICATION_HOUR = "notification_hour"
        private const val KEY_NOTIFICATION_MINUTE = "notification_minute"
        private const val KEY_CURRENT_KURAL = "current_kural"
        private const val KEY_LAST_SHOWN_DATE = "last_shown_date"
        private const val TOTAL_KURALS = 1330
        private const val DEFAULT_HOUR = 8
        private const val DEFAULT_MINUTE = 0
    }

    // Language: "en" or "ta"
    fun getLanguage(): String = prefs.getString(KEY_LANGUAGE, "en") ?: "en"
    fun setLanguage(lang: String) = prefs.edit().putString(KEY_LANGUAGE, lang).apply()

    // Notification toggle
    fun isNotificationEnabled(): Boolean = prefs.getBoolean(KEY_NOTIFICATION_ENABLED, true)
    fun setNotificationEnabled(enabled: Boolean) =
        prefs.edit().putBoolean(KEY_NOTIFICATION_ENABLED, enabled).apply()

    // Notification time
    fun getNotificationHour(): Int = prefs.getInt(KEY_NOTIFICATION_HOUR, DEFAULT_HOUR)
    fun getNotificationMinute(): Int = prefs.getInt(KEY_NOTIFICATION_MINUTE, DEFAULT_MINUTE)
    fun setNotificationTime(hour: Int, minute: Int) {
        prefs.edit()
            .putInt(KEY_NOTIFICATION_HOUR, hour)
            .putInt(KEY_NOTIFICATION_MINUTE, minute)
            .apply()
    }

    // Daily kural tracking (sequential 1-1330, then loops)
    fun getTodayKuralNumber(): Int {
        val stored = prefs.getInt(KEY_CURRENT_KURAL, 1)
        return if (stored in 1..TOTAL_KURALS) stored else 1
    }

    fun advanceKural() {
        val current = getTodayKuralNumber()
        val next = if (current >= TOTAL_KURALS) 1 else current + 1
        prefs.edit().putInt(KEY_CURRENT_KURAL, next).apply()
    }

    fun setCurrentKural(number: Int) {
        if (number in 1..TOTAL_KURALS) {
            prefs.edit().putInt(KEY_CURRENT_KURAL, number).apply()
        }
    }

    fun getLastShownDate(): String = prefs.getString(KEY_LAST_SHOWN_DATE, "") ?: ""
    fun setLastShownDate(date: String) = prefs.edit().putString(KEY_LAST_SHOWN_DATE, date).apply()
}
