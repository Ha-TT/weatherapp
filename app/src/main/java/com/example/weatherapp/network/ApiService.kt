package com.example.weatherapp.network

import com.example.weatherapp.network.service.WeatherService

object ApiService {
    fun getWeatherService() = ApiRetrofit.getInstance().create(WeatherService::class.java)
}