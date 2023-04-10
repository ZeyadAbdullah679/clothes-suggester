package com.example.clothingsuggester.domain

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val data: Data,
)

data class Data(
    val time: String,
    @SerializedName("values") val weatherValues: WeatherValues
)

data class WeatherValues(
    val temperature: Double,
    val humidity: Double,
    val windSpeed: Double,
    val cloudCover: Double,
    val weatherCode: Int,
)