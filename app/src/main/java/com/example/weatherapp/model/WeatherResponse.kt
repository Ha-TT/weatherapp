package com.example.weatherapp.model

data class WeatherResponse(
    val city: City,
    val list: List<WeatherData>
)
