package com.github.frayeralex.weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.frayeralex.weather.R
import com.github.frayeralex.weather.models.ForecastListItem
import kotlinx.android.synthetic.main.forecast_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ForecastListAdapter(
    private val dataSet: ArrayList<ForecastListItem>,
    private val click : (ForecastListItem)-> Unit
) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.forecast_list_item, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.itemView.forecastListItem.text = formatDate(item.dt)
        viewHolder.itemView.setOnClickListener { click(item) }
    }

    override fun getItemCount() = dataSet.size

    private fun formatDate(moment: Long): String {
        val sdf = SimpleDateFormat("dd E MMM")
        val date = Date(moment * 1000)
        return sdf.format(date)
    }

    private fun getForecastUrlByCode(icon: String): String = "http://openweathermap.org/img/wn/${icon}@2x.png"
}