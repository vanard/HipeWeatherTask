package com.vanard.hipeweathertask.data.model.daily


import com.google.gson.annotations.SerializedName

data class FutureDailyWeatherResponse(
    val city: City,
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<X>
)