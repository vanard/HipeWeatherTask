package com.vanard.hipeweathertask.data.model.hourly


import com.google.gson.annotations.SerializedName

data class FutureHourlyWeatherResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<X>,
    val city: City
)