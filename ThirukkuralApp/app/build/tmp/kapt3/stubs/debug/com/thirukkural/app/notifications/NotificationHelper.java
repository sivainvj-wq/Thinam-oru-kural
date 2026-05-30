package com.thirukkural.app.notifications;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ&\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/thirukkural/app/notifications/NotificationHelper;", "", "()V", "CHANNEL_ID", "", "CHANNEL_NAME", "EXTRA_KURAL_NUMBER", "NOTIFICATION_ID", "", "createNotificationChannel", "", "context", "Landroid/content/Context;", "showKuralNotification", "kuralNumber", "kuralLine1", "kuralLine2", "app_debug"})
public final class NotificationHelper {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANNEL_ID = "thirukkural_daily";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANNEL_NAME = "Daily Kural";
    public static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String EXTRA_KURAL_NUMBER = "kural_number";
    @org.jetbrains.annotations.NotNull
    public static final com.thirukkural.app.notifications.NotificationHelper INSTANCE = null;
    
    private NotificationHelper() {
        super();
    }
    
    public final void createNotificationChannel(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    public final void showKuralNotification(@org.jetbrains.annotations.NotNull
    android.content.Context context, int kuralNumber, @org.jetbrains.annotations.NotNull
    java.lang.String kuralLine1, @org.jetbrains.annotations.NotNull
    java.lang.String kuralLine2) {
    }
}