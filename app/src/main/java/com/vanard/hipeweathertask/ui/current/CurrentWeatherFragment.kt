package com.vanard.hipeweathertask.ui.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vanard.hipeweathertask.R

class CurrentWeatherFragment : Fragment() {

    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentWeatherViewModel =
            ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_current_weather, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        currentWeatherViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}