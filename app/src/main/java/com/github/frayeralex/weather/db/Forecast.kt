package com.github.frayeralex.weather.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Forecast(
    val dt: Long,
    val temp: Double
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}