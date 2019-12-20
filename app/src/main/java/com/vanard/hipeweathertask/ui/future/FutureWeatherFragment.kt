package com.vanard.hipeweathertask.ui.future

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vanard.hipeweathertask.R

class FutureWeatherFragment : Fragment() {

    private lateinit var futureWeatherViewModel: FutureWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        futureWeatherViewModel =
            ViewModelProviders.of(this).get(FutureWeatherViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_future_weather, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        futureWeatherViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}