package com.vanard.hipeweathertask.ui.future

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vanard.hipeweathertask.R
import com.vanard.hipeweathertask.data.model.daily.X
import com.vanard.hipeweathertask.network.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_future_weather.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class FutureDailyWeatherItem(private val weatherList: ArrayList<X>) : RecyclerView.Adapter<FutureDailyWeatherItem.WeatherViewHolder>() {

//    private var weatherList: List<X> = ArrayList<X>()

    class WeatherViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val weatherDay: AppCompatTextView = itemView.future_day
        private val weatherDegree: AppCompatTextView = itemView.future_temp
        private val weatherIcon: AppCompatImageView = itemView.future_icon

        fun bindItems(weather: X) {
            val date = Date(weather.dt.toLong()*1000)
            val day = SimpleDateFormat("EE", Locale.getDefault()).format(date)
            val url = BASE_IMAGE_URL + weather.weather[0].icon + ".png"
            Glide.with(itemView.context).load(url)
                .into(weatherIcon)

            weatherDay.text = day
            weatherDegree.text = weather.temp.day.roundToInt().toString() + 0x00B0.toChar()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_future_weather, parent, false)

        return WeatherViewHolder(itemView)
    }

    override fun getItemCount() = weatherList.size

    fun getItem(position: Int): X = weatherList[position]

//    fun addItems(list: List<X>) {
//        this.weatherList = list
//        notifyDataSetChanged()
//    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = getItem(position)
        holder.bindItems(weather)
    }

}