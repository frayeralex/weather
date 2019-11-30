package com.github.frayeralex.weather.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastListItem(
    val dt: Long,
    val main: MainWeatherInfo,
    val weather: List<Weather>,
    val wind: Wind,
    val rain: Map<String, Int>?,
    val clouds: Map<String, Int>?
)