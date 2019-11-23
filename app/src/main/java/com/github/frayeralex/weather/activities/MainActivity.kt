package com.github.frayeralex.weather.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.github.frayeralex.weather.R
import com.github.frayeralex.weather.cache.SPCache
import com.github.frayeralex.weather.api.responses.WeatherResponse
import com.github.frayeralex.weather.providers.ServiceProvider
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val cache by lazy { SPCache(this) }
    private val serviceProvider by lazy { ServiceProvider() }
    private val apiClient by lazy { serviceProvider.provideApiClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        primaryTitle.text = cache.metric

        apiClient.weatherService.getCurrentWeather(cache.location + "," + cache.country, cache.metric).enqueue(object: Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<WeatherResponse>,
                                    response: Response<WeatherResponse>) {

                Toast.makeText(applicationContext, response.body()?.main.toString(), Toast.LENGTH_LONG).show()
            }

        })

    }

}
