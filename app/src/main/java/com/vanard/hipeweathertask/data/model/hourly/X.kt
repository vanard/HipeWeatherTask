package com.vanard.hipeweathertask.data.model.hourly


import com.google.gson.annotations.SerializedName
import com.vanard.hipeweathertask.data.model.current.Weather
import com.vanard.hipeweathertask.data.model.current.Wind

data class X(
    val dt: Int,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val sys: Sys,
    @SerializedName("dt_txt")
    val dtTxt: String,
    val rain: Rain
)