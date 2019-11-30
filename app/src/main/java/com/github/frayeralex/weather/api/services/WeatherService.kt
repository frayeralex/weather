package com.github.frayeralex.weather.api.services

import retrofit2.Call
import com.github.frayeralex.weather.api.responses.*
import retrofit2.http.*

const val WEATHER_ENDPOINT = "weather"
const val FORECAST_ENDPOINT = "forecast"

const val SEARCH_QUERY_PARAM = "q"
const val UNIT_QUERY_PARAM = "units"
const val COUNT_QUERY_PARAM = "cnt"

interface WeatherService {

    @GET(WEATHER_ENDPOINT)
    fun getCurrentWeather(
        @Query(SEARCH_QUERY_PARAM) location: String,
        @Query(UNIT_QUERY_PARAM) units: String
    ): Call<WeatherResponse>

    @GET(FORECAST_ENDPOINT)
    fun getForecast(
        @Query(SEARCH_QUERY_PARAM) location: String,
        @Query(UNIT_QUERY_PARAM) units: String,
        @Query(COUNT_QUERY_PARAM) count: String = "40"
    ): Call<ForecastResponse>

}