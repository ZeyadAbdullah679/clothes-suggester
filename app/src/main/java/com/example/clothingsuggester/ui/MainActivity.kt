package com.example.clothingsuggester.ui

import android.os.Build
import android.view.View
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setup() {
        SharedPref.initSharedPref(this)
        ApiRequest.currentWeatherStateRequest(::onFailure, ::onResponse)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun onResponse(response: Response) {
        val weatherState = Gson().fromJson(response.body?.string(), WeatherData::class.java)
        val currentDate: String = LocalDateTime.now().toLocalDate().toString()
        runOnUiThread {
            updateUiData(weatherState.data.weatherValues)
            updateRecommendedOutfit(weatherState.data.weatherValues.temperature, SharedPref.date, currentDate)
            SharedPref.date = currentDate
        }
    }

    private fun updateRecommendedOutfit(temperature: Double, oldDate: String?, newDate: String) {
        val recommendedOutfit =
            DataManager.getRecommendedOutfit(temperature, oldDate, newDate)
        initRecyclerView(recommendedOutfit)
    }

    private fun initRecyclerView(clothes:List<Cloth>) {
        binding.recyclerRecommendedOutfit.adapter = ClothesAdapter(clothes)
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

    private fun onFailure() {
        binding.apply {
            imageWifiOff.visibility = View.VISIBLE
            textTemperature.visibility = View.INVISIBLE
        }
    }
}