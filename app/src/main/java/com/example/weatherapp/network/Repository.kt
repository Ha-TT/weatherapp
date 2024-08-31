package com.example.weatherapp.network

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.model.WeatherResponse

class Repository(
//    private val weatherDao: WeatherDao
) {

    suspend fun getWeatherByCity(city: String, days: Int): Result<WeatherResponse> {
        return try {
            val response = ApiService.getWeatherService().getWeatherByCity(city, days, BuildConfig.APP_ID)
            if (response.isSuccessful) {
                response.body()?.let {
//                    weatherDao.insertWeather(it)  // Lưu vào cache
                    Result.success(it)
                } ?: Result.failure(Exception("No data"))
            } else {
                Result.failure(Exception("Error: ${response.code()} - Message: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

//    fun getCachedWeather(): LiveData<List<WeatherData>> {
//        return weatherDao.getWeatherData()
//    }
}