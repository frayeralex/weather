package com.github.frayeralex.weather.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.frayeralex.weather.App

@Database(entities = arrayOf(Forecast::class), version = 2)
public abstract class AppDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: App): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "DB"
                ).build().let {
                    INSTANCE = it
                    return it
                }
            }
        }
    }
}