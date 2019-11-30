package com.github.frayeralex.weather.interfaces

import com.github.frayeralex.weather.models.ForecastListItem

interface ForecastHandleListItemInterface {
    fun onSelectForecastListItem(item: ForecastListItem)
}