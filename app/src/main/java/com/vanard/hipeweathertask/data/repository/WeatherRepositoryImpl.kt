package com.vanard.hipeweathertask.data.repository

import androidx.lifecycle.LiveData
import com.vanard.hipeweathertask.data.model.CurrentWeatherResponse
import com.vanard.hipeweathertask.network.WeatherNetworkDataSource

class WeatherRepositoryImpl(
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : WeatherRepository {
    override suspend fun getCurrentWeather(): LiveData<CurrentWeatherResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}