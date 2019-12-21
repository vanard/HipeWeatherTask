package com.vanard.hipeweathertask.ui.future

import androidx.lifecycle.ViewModel
import com.vanard.hipeweathertask.data.repository.WeatherRepository
import com.vanard.hipeweathertask.utils.lazyDeferred

class FutureWeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val weather by lazyDeferred {
        weatherRepository.getFutureDailyWeather()
    }

}