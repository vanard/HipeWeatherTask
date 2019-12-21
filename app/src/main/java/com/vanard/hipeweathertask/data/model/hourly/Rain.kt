package com.vanard.hipeweathertask.data.model.hourly


import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("3h")
    val h: Double
)