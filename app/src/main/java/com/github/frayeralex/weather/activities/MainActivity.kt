package com.github.frayeralex.weather.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.github.frayeralex.weather.R
import com.github.frayeralex.weather.models.*
import com.github.frayeralex.weather.api.responses.ForecastResponse
import com.github.frayeralex.weather.cache.SPCache
import com.github.frayeralex.weather.fragments.*
import com.github.frayeralex.weather.interfaces.*
import com.github.frayeralex.weather.providers.*
import retrofit2.*

class MainActivity : AppCompatActivity(), ForecastDataProviderInterface, ForecastHandleListItemInterface {

    private val cache by lazy { SPCache(this) }
    private val serviceProvider by lazy { ServiceProvider() }
    private val apiClient by lazy { serviceProvider.provideApiClient() }
    private var forecastList: ArrayList<ForecastListItem> = arrayListOf()
    private var selectedForecast: ForecastListItem? = null
    private val forecastDataSubscribers: ArrayList<(data: ArrayList<ForecastListItem>) -> Unit> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.main_content_fragment,
                    ForecastListFragment()
                )
                commit()
            }
        }
        fetchForecast()
    }

    private fun showDetails() {
        if (resources.getBoolean(R.bool.isLand)) {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.details_fragment,
                    DetailsViewFragment()
                )
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.main_content_fragment,
                    DetailsViewFragment()
                )
                addToBackStack(null)
                commit()
            }
        }
    }

    fun goBack(v: View) {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        }
    }

    override fun subscribeForecastList(handler: (data: ArrayList<ForecastListItem>) -> Unit) {
        forecastDataSubscribers.add(handler)
    }

    override fun onSelectForecastListItem(item: ForecastListItem) {
        selectedForecast = item
        showDetails()
    }

    override fun getCurrentForecast(): ForecastListItem? = selectedForecast


    fun fetchForecast() {
        apiClient.weatherService.getForecast(cache.location + "," + cache.country, cache.metric).enqueue(object: Callback<ForecastResponse> {
            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                handleFetchForecastFailure(call, t)
            }

            override fun onResponse(call: Call<ForecastResponse>,
                                    response: Response<ForecastResponse>) {
                handleFetchForecastSuccess(call, response)
            }
        })
    }

    fun handleFetchForecastFailure (call: Call<ForecastResponse>, t: Throwable) {
        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
        Log.d("forecastList__error", t.message)
    }

    fun handleFetchForecastSuccess (call: Call<ForecastResponse>, response: Response<ForecastResponse>) {
        if (response.body()?.list is List) {
            forecastList = response.body()?.list as ArrayList<ForecastListItem>
            forecastDataSubscribers.forEach{ handler -> handler(forecastList) }
        }
        Log.d("forecastList_handleFetchForecastSuccess", forecastList.toString())
    }
}
