package com.github.frayeralex.weather.api.responses

import com.squareup.moshi.JsonClass
import com.github.frayeralex.weather.models.*

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val id: Long,
    val main: MainWeatherInfo,
    val weather: List<Weather>,
    val wind: Wind,
    val rain: Map<String, Int>?,
    val clouds: Map<String, Int>?
)