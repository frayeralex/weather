package com.github.frayeralex.weather.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ForecastDao {
    @Query("SELECT * FROM forecast")
    suspend fun getAll(): List<Forecast>

    @Query("SELECT * FROM forecast WHERE uid IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<Forecast>

    @Insert
    suspend fun insertAll(vararg forecasts: Forecast)

    @Delete
    suspend fun delete(forecast: Forecast)
}