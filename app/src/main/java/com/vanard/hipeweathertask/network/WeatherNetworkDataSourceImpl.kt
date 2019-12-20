package com.vanard.hipeweathertask.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vanard.hipeweathertask.data.model.CurrentWeatherResponse
import com.vanard.hipeweathertask.utils.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val apiService: ApiService
) : WeatherNetworkDataSource {

    private val _downloadCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, key: String) {
        try {
            val fetchCurrentWeather = apiService
                .getCurrentWeather(location, key)
                .await()
            _downloadCurrentWeather.postValue(fetchCurrentWeather)
        }catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}