package com.example.clothingsuggester.util

import okhttp3.*
import java.io.IOException

object ApiRequest {

    private val client = OkHttpClient()

    fun currentWeatherStateRequest(
        onFailure: () -> Unit,
        onResponse: (response: Response) -> Unit,
    ) {

        val url = HttpUrl.Builder()
            .scheme("https")
            .host("api.tomorrow.io")
            .addPathSegment("v4")
            .addPathSegment("weather")
            .addPathSegment("realtime")
            .addQueryParameter("location", "tanta")
            .addQueryParameter("apikey", Constants.API_KEY)
            .build()

        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) = onFailure()
            override fun onResponse(call: Call, response: Response) = onResponse(response)
        })
    }

}