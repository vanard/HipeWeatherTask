package com.vanard.hipeweathertask.ui.current

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.vanard.hipeweathertask.R
import com.vanard.hipeweathertask.data.model.current.CurrentWeatherResponse
import com.vanard.hipeweathertask.data.model.hourly.FutureHourlyWeatherResponse
import com.vanard.hipeweathertask.network.BASE_IMAGE_URL
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_current_weather.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import kotlin.math.roundToInt

class CurrentWeatherFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentWeatherViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CurrentWeatherViewModel::class.java)
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindUI()
    }

    private fun bindUI() = GlobalScope.launch {
        val weatherObserver = getWeatherObserver()
        val currentWeather = currentWeatherViewModel.weather.await()
        currentWeather.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(weatherObserver)

        val hourlyWeatherObserver = getHourlyWeatherObserver()
        val hourlyWeather = currentWeatherViewModel.hourlyWeather.await()
        hourlyWeather.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(hourlyWeatherObserver)

    }

    private fun getHourlyWeatherObserver(): DisposableObserver<FutureHourlyWeatherResponse> {
        return object : DisposableObserver<FutureHourlyWeatherResponse>() {
            override fun onNext(hourlyWeatherResponse: FutureHourlyWeatherResponse) {
                //
            }

            override fun onError(e: Throwable) {
                d("error", e.message)
            }

            override fun onComplete() {
            }
        }
    }

    private fun getWeatherObserver(): DisposableObserver<CurrentWeatherResponse> {
        return object : DisposableObserver<CurrentWeatherResponse>() {
            override fun onNext(currentWeather: CurrentWeatherResponse) {
                //
                val main = currentWeather.main
                val weather = currentWeather.weather[0]
                val temp = main.temp.roundToInt()
                val feelsLike = main.feelsLike.roundToInt()
                val url = BASE_IMAGE_URL + weather.icon + ".png"

                Glide.with(this@CurrentWeatherFragment).load(url)
                    .into(current_icon)

                if (Integer.valueOf(temp) > 24)
                    current_temp_icon.setImageResource(R.drawable.ic_hot)
                else
                    current_temp_icon.setImageResource(R.drawable.ic_cold)

                if (Integer.valueOf(temp) > 24)
                    current_feels_like_icon.setImageResource(R.drawable.ic_hot)
                else
                    current_feels_like_icon.setImageResource(R.drawable.ic_cold)

                current_text.text = temp.toString() + 0x00B0.toChar() + "C"
                current_description.text = weather.main + " | " + weather.description

                current_temp_text.text = temp.toString() + 0x00B0.toChar()
                current_feels_like_text.text = feelsLike.toString() + 0x00B0.toChar()

                current_humidity_text.text = "${main.humidity}%"
                current_wind_text.text = "${currentWeather.wind.speed} km/h"
            }

            override fun onError(e: Throwable) {
                d("error", e.message)
            }

            override fun onComplete() {
            }
        }
    }


}