package com.vanard.hipeweathertask

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.vanard.hipeweathertask.data.repository.WeatherRepository
import com.vanard.hipeweathertask.data.repository.WeatherRepositoryImpl
import com.vanard.hipeweathertask.network.ApiService
import com.vanard.hipeweathertask.network.ConnectivityInterceptor
import com.vanard.hipeweathertask.network.ConnectivityInterceptorImpl
import com.vanard.hipeweathertask.ui.current.CurrentWeatherViewModelFactory
import com.vanard.hipeweathertask.ui.future.FutureWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class HipeApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@HipeApplication))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind<WeatherRepository>() with singleton { WeatherRepositoryImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance()) }
        bind() from provider { FutureWeatherViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}