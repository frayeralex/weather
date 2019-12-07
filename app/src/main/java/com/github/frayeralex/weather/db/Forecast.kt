package com.github.frayeralex.weather.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Forecast(
    @PrimaryKey val uid: Int,
    val dt: Long,
    val temp: Double
)