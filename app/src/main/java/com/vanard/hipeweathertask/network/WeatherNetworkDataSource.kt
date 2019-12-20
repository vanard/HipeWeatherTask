package com.vanard.hipeweathertask.network

import androidx.lifecycle.LiveData
import com.vanard.hipeweathertask.data.model.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        key: String
    )
}