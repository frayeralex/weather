package com.github.frayeralex.weather.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import com.github.frayeralex.weather.R
import com.github.frayeralex.weather.cache.SPCache
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val cache by lazy { SPCache(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        primaryTitle.text = cache.country
    }

}
