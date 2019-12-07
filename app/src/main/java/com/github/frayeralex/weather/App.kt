package com.github.frayeralex.weather

import android.app.Application
import com.github.frayeralex.weather.db.AppDatabase

class App : Application() {
    fun getDatabase(): AppDatabase {
        return AppDatabase.getDatabase(this)
    }
}