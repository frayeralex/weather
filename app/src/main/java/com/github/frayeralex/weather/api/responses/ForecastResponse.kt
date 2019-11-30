package com.github.frayeralex.weather.api.responses

import com.github.frayeralex.weather.models.ForecastListItem
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    val list: List<ForecastListItem>,
    val cnt: Int
)