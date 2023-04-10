package com.example.clothingsuggester.ui

import android.util.Log
import com.example.clothingsuggester.databinding.ActivityMainBinding
import com.example.clothingsuggester.domain.WeatherData
import com.example.clothingsuggester.util.ApiRequest
import com.example.clothingsuggestor.ui.BaseActivity
import com.google.gson.Gson
import okhttp3.Response

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun setup() {
        ApiRequest.currentWeatherStateRequest(::onFailure, ::onResponse)
    }

    private fun onResponse(response: Response) {
        val weatherState = Gson().fromJson(response.body?.string(), WeatherData::class.java)
    }

    private fun onFailure() {

    }
}