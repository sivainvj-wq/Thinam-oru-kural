# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep Room entities
-keep class com.thirukkural.app.model.** { *; }
-keep class com.thirukkural.app.data.db.** { *; }

# Keep Gson serialization
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Keep WorkManager
-keep class androidx.work.** { *; }

# Keep Navigation
-keep class androidx.navigation.** { *; }

# Suppress warnings
-dontwarn kotlin.**
-dontwarn kotlinx.**
