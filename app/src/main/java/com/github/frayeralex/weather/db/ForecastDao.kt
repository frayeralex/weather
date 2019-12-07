package com.github.frayeralex.weather.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ForecastDao {
    @Query("SELECT * FROM forecast")
    fun getAll(): List<Forecast>

    @Query("SELECT * FROM forecast WHERE uid IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Forecast>

    @Insert
    fun insertAll(vararg forecasts: Forecast)

    @Delete
    fun delete(forecast: Forecast)
}