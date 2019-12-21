package com.vanard.hipeweathertask.data.repository

import com.vanard.hipeweathertask.data.model.current.CurrentWeatherResponse
import com.vanard.hipeweathertask.data.model.daily.FutureDailyWeatherResponse
import com.vanard.hipeweathertask.data.model.hourly.FutureHourlyWeatherResponse
import io.reactivex.Observable

interface WeatherRepository {
    suspend fun getCurrentWeather(): Observable<CurrentWeatherResponse>

    suspend fun getFutureDailyWeather(): Observable<FutureDailyWeatherResponse>

    suspend fun getFutureHourlyWeather(): Observable<FutureHourlyWeatherResponse>
}