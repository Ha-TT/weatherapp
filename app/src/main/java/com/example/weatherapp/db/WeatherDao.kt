package com.example.weatherapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM weather WHERE keywordSearch = :keywordSearch")
    suspend fun getWeatherCache(keywordSearch: String): WeatherEntity?
}