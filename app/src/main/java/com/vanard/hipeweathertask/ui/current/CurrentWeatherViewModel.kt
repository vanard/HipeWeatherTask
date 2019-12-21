package com.vanard.hipeweathertask.ui.current

import androidx.lifecycle.ViewModel
import com.vanard.hipeweathertask.data.repository.WeatherRepository
import com.vanard.hipeweathertask.utils.lazyDeferred

class CurrentWeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val weather by lazyDeferred {
        weatherRepository.getCurrentWeather()
    }

    val hourlyWeather by lazyDeferred {
        weatherRepository.getFutureHourlyWeather()
    }
}