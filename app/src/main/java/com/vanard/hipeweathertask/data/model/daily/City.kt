package com.vanard.hipeweathertask.data.model.daily


import com.google.gson.annotations.SerializedName
import com.vanard.hipeweathertask.data.model.current.Coord

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int
)