package com.example.weatherapp.network

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.db.WeatherDao
import com.example.weatherapp.db.WeatherEntity
import com.example.weatherapp.model.WeatherResponse
import com.google.gson.Gson;

class Repository(
    private val weatherDao: WeatherDao
) {

    suspend fun getWeatherByCity(city: String, days: Int): Result<WeatherResponse> {
        return try {
            // Check data in database
            val cachedWeather = weatherDao.getWeatherCache(city)
            if (cachedWeather != null) {
                return Result.success(Gson().fromJson(cachedWeather.weatherDataJson, WeatherResponse::class.java))
            }

            // If there is no data in the database, call the API
            val response = ApiService.getWeatherService().getWeatherByCity(city, days, BuildConfig.APP_ID)
            if (response.isSuccessful) {
                response.body()?.let {
                    // Save to DB
                    val entity = WeatherEntity(city, Gson().toJson(it))
                    weatherDao.insertWeather(entity)

                    Result.success(it)
                } ?: Result.failure(Exception("No data"))
            } else {
                Result.failure(Exception("Error: ${response.code()} - Message: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}