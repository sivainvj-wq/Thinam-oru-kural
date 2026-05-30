package com.thirukkural.app.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\rJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\rJ\u000e\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\nJ\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\nJ\u000e\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0011J\u0016\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/thirukkural/app/utils/PreferencesManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "advanceKural", "", "getLanguage", "", "getLastShownDate", "getNotificationHour", "", "getNotificationMinute", "getTodayKuralNumber", "isNotificationEnabled", "", "setCurrentKural", "number", "setLanguage", "lang", "setLastShownDate", "date", "setNotificationEnabled", "enabled", "setNotificationTime", "hour", "minute", "Companion", "app_debug"})
public final class PreferencesManager {
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREF_NAME = "thirukkural_prefs";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_LANGUAGE = "language";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_NOTIFICATION_ENABLED = "notification_enabled";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_NOTIFICATION_HOUR = "notification_hour";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_NOTIFICATION_MINUTE = "notification_minute";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_CURRENT_KURAL = "current_kural";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_LAST_SHOWN_DATE = "last_shown_date";
    private static final int TOTAL_KURALS = 1330;
    private static final int DEFAULT_HOUR = 8;
    private static final int DEFAULT_MINUTE = 0;
    @org.jetbrains.annotations.NotNull
    public static final com.thirukkural.app.utils.PreferencesManager.Companion Companion = null;
    
    public PreferencesManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLanguage() {
        return null;
    }
    
    public final void setLanguage(@org.jetbrains.annotations.NotNull
    java.lang.String lang) {
    }
    
    public final boolean isNotificationEnabled() {
        return false;
    }
    
    public final void setNotificationEnabled(boolean enabled) {
    }
    
    public final int getNotificationHour() {
        return 0;
    }
    
    public final int getNotificationMinute() {
        return 0;
    }
    
    public final void setNotificationTime(int hour, int minute) {
    }
    
    public final int getTodayKuralNumber() {
        return 0;
    }
    
    public final void advanceKural() {
    }
    
    public final void setCurrentKural(int number) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLastShownDate() {
        return null;
    }
    
    public final void setLastShownDate(@org.jetbrains.annotations.NotNull
    java.lang.String date) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/thirukkural/app/utils/PreferencesManager$Companion;", "", "()V", "DEFAULT_HOUR", "", "DEFAULT_MINUTE", "KEY_CURRENT_KURAL", "", "KEY_LANGUAGE", "KEY_LAST_SHOWN_DATE", "KEY_NOTIFICATION_ENABLED", "KEY_NOTIFICATION_HOUR", "KEY_NOTIFICATION_MINUTE", "PREF_NAME", "TOTAL_KURALS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}