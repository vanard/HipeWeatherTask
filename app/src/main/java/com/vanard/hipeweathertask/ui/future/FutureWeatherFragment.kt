package com.vanard.hipeweathertask.ui.future

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vanard.hipeweathertask.R
import com.vanard.hipeweathertask.data.model.daily.FutureDailyWeatherResponse
import com.vanard.hipeweathertask.data.model.hourly.FutureHourlyWeatherResponse
import com.vanard.hipeweathertask.ui.current.CurrentWeatherViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FutureWeatherFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var futureWeatherViewModel: FutureWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        futureWeatherViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FutureWeatherViewModel::class.java)
        return inflater.inflate(R.layout.fragment_future_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindUI()
    }

    private fun bindUI() = GlobalScope.launch {
        val weatherObserver = getWeatherObserver()
        val currentWeather = futureWeatherViewModel.weather.await()
        currentWeather.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(weatherObserver)


    }

    private fun getWeatherObserver() : DisposableObserver<FutureDailyWeatherResponse> {
        return object : DisposableObserver<FutureDailyWeatherResponse>() {
            override fun onNext(weatherResponse: FutureDailyWeatherResponse) {
                //
            }

            override fun onError(e: Throwable) {
                Log.d("error", e.message)
            }

            override fun onComplete() {
            }
        }
    }
}