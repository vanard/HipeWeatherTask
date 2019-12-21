package com.vanard.hipeweathertask.ui.future

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vanard.hipeweathertask.data.repository.WeatherRepository

class FutureWeatherViewModelFactory(
    private val weatherRepository: WeatherRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureWeatherViewModel(weatherRepository) as T
    }
}