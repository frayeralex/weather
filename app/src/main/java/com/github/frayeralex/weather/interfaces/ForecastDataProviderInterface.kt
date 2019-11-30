package com.github.frayeralex.weather.interfaces

import com.github.frayeralex.weather.models.ForecastListItem

interface ForecastDataProviderInterface {
    fun getCurrentForecast(): ForecastListItem?
    fun subscribeForecastList(handler: (data: ArrayList<ForecastListItem>) -> Unit)
}