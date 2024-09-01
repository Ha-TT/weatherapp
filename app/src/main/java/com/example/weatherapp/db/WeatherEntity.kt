package com.example.weatherapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey val keywordSearch: String,
    val weatherDataJson: String
)