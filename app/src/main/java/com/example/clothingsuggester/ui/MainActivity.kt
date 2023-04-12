package com.example.clothingsuggester.ui

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.clothingsuggester.data.DataManager
import com.example.clothingsuggester.databinding.ActivityMainBinding
import com.example.clothingsuggester.domain.Cloth
import com.example.clothingsuggester.domain.WeatherCodes
import com.example.clothingsuggester.domain.WeatherData
import com.example.clothingsuggester.domain.WeatherValues
import com.example.clothingsuggester.ui.adapters.ClothesAdapter
import com.example.clothingsuggester.util.ApiRequest
import com.example.clothingsuggester.util.SharedPref
import com.example.clothingsuggestor.ui.BaseActivity
import com.google.gson.Gson
import okhttp3.Response
import java.time.LocalDateTime
import kotlin.math.roundToInt

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun setup() {
        SharedPref.initSharedPref(this)
        ApiRequest.currentWeatherStateRequest(::onFailure, ::onResponse)
    }

    private fun initRecyclerView(clothes:List<Cloth>) {
        binding.recyclerRecommendedOutfit.adapter = ClothesAdapter(clothes)
    }

    private fun onResponse(response: Response) {
        val weatherState = Gson().fromJson(response.body?.string(), WeatherData::class.java)
        runOnUiThread {
            updateUiData(weatherState.data.weatherValues)
            updateRecommendedOutfit(weatherState.data.weatherValues.temperature)
        }
    }

    private fun updateRecommendedOutfit(temperature: Double) {
        val recommendedOutfit =
            DataManager.getRecommendedOutfit(temperature)
        initRecyclerView(recommendedOutfit)
    }

    private fun updateUiData(weather: WeatherValues) {
        binding.apply {
            textWeatherState.text = WeatherCodes.weatherCode[weather.weatherCode]
            textTemperature.text = weather.temperature.roundToInt().toString().plus(" °C")
            textHumidityValue.text = weather.humidity.roundToInt().toString().plus(" %")
            textWindValue.text = weather.windSpeed.roundToInt().toString().plus(" m/s")
            textCloudCoverValue.text = weather.cloudCover.roundToInt().toString().plus(" %")
        }
    }

    private fun onFailure() {}
}