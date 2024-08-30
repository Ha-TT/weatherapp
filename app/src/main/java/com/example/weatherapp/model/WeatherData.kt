package com.example.weatherapp.model

data class WeatherData(
    val dt: Long,
    val temp: Temp,
    val weather: List<WeatherDescription>
)