package com.github.frayeralex.weather.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.frayeralex.weather.R
import com.github.frayeralex.weather.adapters.ForecastListAdapter
import com.github.frayeralex.weather.models.ForecastListItem
import com.github.frayeralex.weather.interfaces.ForecastDataProviderInterface
import com.github.frayeralex.weather.interfaces.ForecastHandleListItemInterface
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ForecastListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var forecastList: ArrayList<ForecastListItem>
    private val handlerContext by lazy { context as? ForecastHandleListItemInterface }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeToForecast()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.forescast_list_frag,
            container, false
        ).apply { tag =
            TAG
        }

        recyclerView = rootView.findViewById(R.id.forecastListView)

        layoutManager = LinearLayoutManager(activity)

        setRecyclerViewLayoutManager()

        val handlerInterface = activity as? ForecastHandleListItemInterface

        if (handlerInterface != null) {
            recyclerView.adapter = ForecastListAdapter(forecastList){handlerContext?.onSelectForecastListItem(it)}
        }

        return rootView
    }

    private fun setRecyclerViewLayoutManager() {
        var scrollPosition = 0

        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }

        layoutManager = LinearLayoutManager(activity)

        with(recyclerView) {
            layoutManager = this@ForecastListFragment.layoutManager
            scrollToPosition(scrollPosition)
        }
    }

    private fun subscribeToForecast() {
        forecastList = arrayListOf()
        (activity as? ForecastDataProviderInterface)?.let {
            it.subscribeForecastList{ data -> handleForecastChange(data) }

        }
    }

    private fun handleForecastChange(data: ArrayList<ForecastListItem>) {
        forecastList = data
        recyclerView.adapter = ForecastListAdapter(getFirstDaysForecastItemList()){handlerContext?.onSelectForecastListItem(it)}
    }

    private fun getFirstDaysForecastItemList(): ArrayList<ForecastListItem> {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var lastDayLabel: String = ""
        val dayItemList = arrayListOf<ForecastListItem>();

        forecastList.forEach {
            val dayLabel: Date = Date(it.dt * 1000)
            if (lastDayLabel != sdf.format(dayLabel)) {
                lastDayLabel = sdf.format(dayLabel)
                dayItemList.add(it)
            }
        }
        return dayItemList
    }

    companion object {
        private val TAG = "ForecastListFragment"
    }
}