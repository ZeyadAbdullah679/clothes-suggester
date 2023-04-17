package com.example.clothingsuggester.domain

data class Cloth (
    val id: Int,
    val imageSource: Int,
    val minPreferredTemp: Double,
    val maxPreferredTemp: Double
)
