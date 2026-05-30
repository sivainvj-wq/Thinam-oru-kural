package com.thirukkural.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thirukkural.app.model.Kural

@Database(entities = [Kural::class], version = 1, exportSchema = false)
abstract class KuralDatabase : RoomDatabase() {

    abstract fun kuralDao(): KuralDao

    companion object {
        @Volatile
        private var INSTANCE: KuralDatabase? = null

        fun getDatabase(context: Context): KuralDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KuralDatabase::class.java,
                    "thirukkural_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
