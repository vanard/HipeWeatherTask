package com.vanard.hipeweathertask.data.model.daily


import com.google.gson.annotations.SerializedName
import com.vanard.hipeweathertask.data.model.current.Weather

data class X(
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val weather: List<Weather>,
    val speed: Double,
    val deg: Int,
    val clouds: Int,
    val rain: Double
)