package com.example.weatherapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.db.WeatherDatabase
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.Repository
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository
    val weatherLiveData: MutableLiveData<WeatherResponse> = MutableLiveData()
    val editTextLiveData: MutableLiveData<String> = MutableLiveData()
    val messageLiveData: MutableLiveData<String> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    init {
        val weatherDao = WeatherDatabase.getDatabase(application).weatherDao()
        repository = Repository(weatherDao)
    }

    fun fetchNextWeekWeatherByCity(city: String) {
        isLoading.value = true
        viewModelScope.launch {
            repository.getWeatherByCity(city, 7).onSuccess {
                weatherLiveData.postValue(it)
                messageLiveData.postValue("City: + ${it.city.name}")
            }.onFailure {
                messageLiveData.postValue("Error: ${it.message}")
            }.also {
                isLoading.postValue(false)
            }
        }
    }

    fun onSearchButtonClicked() {
        val city = editTextLiveData.value
        if (city != null && city.length >= 3) {
            fetchNextWeekWeatherByCity(city)
        } else {
            messageLiveData.postValue("Search term must be at least 3 characters long.")
        }
    }
}