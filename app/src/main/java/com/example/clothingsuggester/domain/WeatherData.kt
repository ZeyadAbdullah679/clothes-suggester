package com.example.clothingsuggester.domain

data class WeatherData(
    val temperature: Double,
    val humidity: Double,
    val windSpeed: Double,
    val cloudCover: Double,
    val weatherCode: Int,
)