package com.github.frayeralex.weather.api.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    val list: List<WeatherResponse>,
    val cnt: Int
)