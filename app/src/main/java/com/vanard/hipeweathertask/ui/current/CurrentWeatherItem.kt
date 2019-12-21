package com.vanard.hipeweathertask.ui.current

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vanard.hipeweathertask.R
import com.vanard.hipeweathertask.data.model.hourly.X
import com.vanard.hipeweathertask.network.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_weather.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class CurrentWeatherItem() : RecyclerView.Adapter<CurrentWeatherItem.WeatherViewHolder>() {

    private var weatherList: List<X> = ArrayList<X>()

    class WeatherViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val weatherDay: AppCompatTextView = itemView.weather_item_hour
        private val weatherDegree: AppCompatTextView = itemView.weather_item_temp
        private val weatherIcon: AppCompatImageView = itemView.weather_item_icon

        fun bindItems(weather: X) {
            val date = Date(weather.dt.toLong())
            val day = SimpleDateFormat("EE", Locale.getDefault()).format(date)
            val url = BASE_IMAGE_URL + weather.weather[0].icon + ".png"
            Glide.with(itemView.context).load(url)
                .into(weatherIcon)

            weatherDay.text = day
            weatherDegree.text = weather.main.temp.roundToInt().toString() + 0x00B0.toChar()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)

        return WeatherViewHolder(itemView)
    }

    override fun getItemCount() = weatherList.size

    fun getItem(position: Int): X = weatherList[position]

    fun addItems(list: List<X>) {
        this.weatherList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = getItem(position)
        holder.bindItems(weather)
    }

}