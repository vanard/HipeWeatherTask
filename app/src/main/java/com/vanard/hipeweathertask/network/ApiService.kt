package com.vanard.hipeweathertask.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vanard.hipetask.network.ConnectivityInterceptor
import com.vanard.hipeweathertask.data.model.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
const val API_KEY = "6eac656e9e89aba987e082468e7b534a"

interface ApiService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("q") query: String,
        @Query("appid") appid: String

    ): Deferred<CurrentWeatherResponse>

    @GET("forecast/daily")
    fun getDailyWeather(
        @Query("q") query: String,
        @Query("cnt") count: String = "7",
        @Query("appid") appid: String = API_KEY
    )

    @GET("forecast/daily")
    fun getHourlyWeather(
        @Query("q") query: String,
        @Query("cnt") count: String = "7",
        @Query("appid") appid: String = API_KEY
    )

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}