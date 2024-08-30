package com.example.weatherapp.network.service

import com.example.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/forecast/daily")
    fun getWeatherByCity(
        @Query("q") city: String,
        @Query("cnt") days: Int,
        @Query("appid") appId: String
    ): Call<WeatherResponse>
}