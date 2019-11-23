package com.github.frayeralex.weather.api

import com.github.frayeralex.weather.api.services.WeatherService
import retrofit2.Retrofit

class ApiClient(retrofit: Retrofit) {

    val weatherService: WeatherService = retrofit.create(WeatherService::class.java)
}