package com.example.clothingsuggester.ui

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.example.clothingsuggester.data.DataManager
import com.example.clothingsuggester.databinding.ActivityMainBinding
import com.example.clothingsuggester.domain.Cloth
import com.example.clothingsuggester.domain.WeatherCodes
import com.example.clothingsuggester.domain.WeatherData
import com.example.clothingsuggester.ui.adapters.ClothesAdapter
import com.example.clothingsuggester.util.ApiRequest
import com.example.clothingsuggester.util.SharedPref
import com.example.clothingsuggestor.ui.BaseActivity
import com.google.gson.Gson
import com.google.gson.JsonObject
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
        val weatherState = gsonParser(response)
        val currentDate: String = LocalDateTime.now().toLocalDate().toString()
        runOnUiThread {
            updateUiData(weatherState)
            updateRecommendedOutfit(weatherState.temperature, SharedPref.date, currentDate)
            SharedPref.date = currentDate
        }
    }

    private fun gsonParser(response: Response):WeatherData {
        val data = Gson().fromJson(response.body?.string(), JsonObject::class.java)
            .getAsJsonObject("data").getAsJsonObject("values")
        return WeatherData(
            temperature = data.get("temperature")?.asDouble?:0.0,
            humidity = data.get("humidity")?.asInt?.toDouble()?:0.0,
            windSpeed = data.get("windSpeed")?.asDouble?:0.0,
            cloudCover = data.get("cloudCover")?.asInt?.toDouble()?:0.0,
            weatherCode = data.get("weatherCode")?.asInt?:0
        )
    }

    private fun updateRecommendedOutfit(temperature: Double, oldDate: String?, newDate: String) {
        val recommendedOutfit =
            DataManager.getRecommendedOutfit(temperature, oldDate, newDate)
        initRecyclerView(recommendedOutfit)
    }

    private fun initRecyclerView(clothes:List<Cloth>) {
        binding.recyclerRecommendedOutfit.adapter = ClothesAdapter(clothes)
    }

    private fun updateUiData(weather: WeatherData) {
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