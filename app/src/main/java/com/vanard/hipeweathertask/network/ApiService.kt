package com.vanard.hipeweathertask.network

import com.vanard.hipeweathertask.data.model.current.CurrentWeatherResponse
import com.vanard.hipeweathertask.data.model.daily.FutureDailyWeatherResponse
import com.vanard.hipeweathertask.data.model.hourly.FutureHourlyWeatherResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val API_KEY = "6eac656e9e89aba987e082468e7b534a"
const val UNITS = "metric"
const val BASE_IMAGE_URL = "https://openweathermap.org/img/w/"

interface ApiService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("q") query: String,
        @Query("units") units: String = UNITS,
        @Query("appid") appid: String = API_KEY

    ): Observable<CurrentWeatherResponse>

    @GET("forecast/daily")
    fun getDailyWeather(
        @Query("q") query: String,
        @Query("cnt") count: String = "7",
        @Query("units") units: String = UNITS,
        @Query("appid") appid: String = API_KEY
    ): Observable<FutureDailyWeatherResponse>

    @GET("forecast")
    fun getHourlyWeather(
        @Query("q") query: String,
        @Query("cnt") count: String = "7",
        @Query("units") units: String = UNITS,
        @Query("appid") appid: String = API_KEY
    ): Observable<FutureHourlyWeatherResponse>

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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}