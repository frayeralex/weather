package com.github.frayeralex.weather.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.frayeralex.weather.R
import com.github.frayeralex.weather.interfaces.ForecastDataProviderInterface
import com.github.frayeralex.weather.models.ForecastListItem
import kotlinx.android.synthetic.main.day_forecast_details_frag.*

class DetailsViewFragment : Fragment() {
    private var forecast: ForecastListItem? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val provider = activity as ForecastDataProviderInterface
        forecast = provider.getCurrentForecast()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.day_forecast_details_frag,
            container, false
        ).apply { tag =
            TAG
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        temperature.text = forecast?.main?.temp.toString()
    }

    companion object {
        private val TAG = "DetailsViewFragment"
    }
}