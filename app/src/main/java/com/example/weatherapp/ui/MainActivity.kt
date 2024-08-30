package com.example.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.ApiService
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = ApiService.getWeatherService()
        apiService.getWeatherByCity("saigon", 7, BuildConfig.APP_ID).enqueue(object : retrofit2.Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: retrofit2.Response<WeatherResponse>
            ) {
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            }
        })
    }
}