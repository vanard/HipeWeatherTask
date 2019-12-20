package com.vanard.hipeweathertask.data.repository

import androidx.lifecycle.LiveData
import com.vanard.hipeweathertask.data.model.CurrentWeatherResponse

interface WeatherRepository {
    suspend fun getCurrentWeather(): LiveData<CurrentWeatherResponse>
}