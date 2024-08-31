package com.example.weatherapp.model

data class WeatherData(
    val dt: Long,
    val temp: Temp,
    val pressure: Long,
    val humidity: Long,
    val weather: List<WeatherDescription>
) {
    val weatherDescription: String
        get() = weather.firstOrNull()?.description ?: "No description available"
}
