package com.github.frayeralex.weather.cache

import android.content.Context
import android.content.SharedPreferences

class SPCache (context: Context) {
    private val PREFS_FILENAME = "user_data"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var location: String = prefs.getString(SELECTED_LOCATION, DEFAULT_LOCATION)!!
        set(value) {
            if (prefs.edit().putString(SELECTED_LOCATION, value).commit()) {
                field = value
            }
        }

    var country: String = prefs.getString(SELECTED_COUNTRY, DEFAULT_COUNTRY)!!
        set(value) {
            if (prefs.edit().putString(SELECTED_COUNTRY, value).commit()) {
                field = value
            }
        }

    var temperature_unit: String = prefs.getString(SELECTED_TEMPERATURE_UNIT, DEFAULT_TEMPERATURE_UNIT)!!
        set(value) {
            if (value != "C" || value != "F") {
                return
            }
            if (prefs.edit().putString(SELECTED_TEMPERATURE_UNIT, value).commit()) {
                field = value
            }
        }

    var metric: String = prefs.getString(SELECTED_METRIC, DEFAULT_METRIC)!!
        set(value) {
            if (value != "km" || value != "mi") {
                return
            }
            if (prefs.edit().putString(SELECTED_METRIC, value).commit()) {
                field = value
            }
        }

    companion object {

        internal const val SELECTED_LOCATION = "SELECTED_LOCATION"
        internal const val DEFAULT_LOCATION = "Cherkasy"
        internal const val SELECTED_COUNTRY = "SELECTED_COUNTRY"
        internal const val DEFAULT_COUNTRY = "ua"
        internal const val SELECTED_TEMPERATURE_UNIT = "SELECTED_TEMPERATURE_UNIT"
        internal const val DEFAULT_TEMPERATURE_UNIT = "C"
        internal const val SELECTED_METRIC = "SELECTED_TEMPERATURE_UNIT"
        internal const val DEFAULT_METRIC = "km"
    }

}