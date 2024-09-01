package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemWeatherDayBinding
import com.example.weatherapp.model.WeatherData

class WeatherListAdapter(private var weatherList: List<WeatherData>) : RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>() {

    inner class WeatherViewHolder(val binding: ItemWeatherDayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemWeatherDayBinding.inflate(layoutInflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.binding.weather = weatherList[position]
    }

    override fun getItemCount(): Int = weatherList.size

    fun submitList(list: List<WeatherData>) {
        weatherList = list
        notifyDataSetChanged()
    }
}