package com.vanard.hipeweathertask.data.repository

import com.vanard.hipeweathertask.data.model.current.CurrentWeatherResponse
import com.vanard.hipeweathertask.data.model.daily.FutureDailyWeatherResponse
import com.vanard.hipeweathertask.data.model.hourly.FutureHourlyWeatherResponse
import com.vanard.hipeweathertask.network.ApiService
import io.reactivex.Observable

class WeatherRepositoryImpl(
    private val apiService: ApiService
) : WeatherRepository {
    override suspend fun getCurrentWeather(): Observable<CurrentWeatherResponse> {
        return apiService.getCurrentWeather(query = "bandung")

    }

    override suspend fun getFutureDailyWeather(): Observable<FutureDailyWeatherResponse> {
        return apiService.getDailyWeather(query = "bandung")
    }

    override suspend fun getFutureHourlyWeather(): Observable<FutureHourlyWeatherResponse> {
        return apiService.getHourlyWeather(query = "bandung")
    }
}