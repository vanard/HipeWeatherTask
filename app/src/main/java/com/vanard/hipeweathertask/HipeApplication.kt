package com.vanard.hipeweathertask

import android.app.Application
import com.vanard.hipetask.network.ConnectivityInterceptor
import com.vanard.hipetask.network.ConnectivityInterceptorImpl
import com.vanard.hipeweathertask.network.ApiService
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class HipeApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@HipeApplication))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApiService(instance()) }
    }
}