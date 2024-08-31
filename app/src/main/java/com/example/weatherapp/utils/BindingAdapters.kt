package com.example.weatherapp.utils

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("formattedDate")
fun setFormattedDate(textView: TextView, date: Long?) {
    date?.let {
        val sdf = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
        val formattedDate = sdf.format(Date(it * 1000))
        textView.text = "Date: $formattedDate"
    }
}

@BindingAdapter("formattedTemperature")
fun setFormattedTemperature(textView: TextView, tempKevin: Double?) {
    tempKevin?.let {
        // Kelvin to C
        val tempC = tempKevin - 273.15

        textView.text = "Average temperature: ${tempC.toInt()}°C"
    }
}

@BindingAdapter("formattedPressure")
fun setFormattedPressure(textView: TextView, pressure: Long?) {
    pressure?.let {
        textView.text = "Pressure: $pressure"
    }
}

@BindingAdapter("formattedHumidity")
fun setFormattedHumidity(textView: TextView, humidity: Long?) {
    humidity?.let {
        textView.text = "Humidity: $humidity%"
    }
}

@BindingAdapter("formattedDescription")
fun setFormattedPressure(textView: TextView, description: String?) {
    description?.let {
        textView.text = "Description: $description"
    }
}

@BindingAdapter("isLoading")
fun setLoading(loadingLayout: FrameLayout, isLoading: Boolean?) {
    isLoading?.let {
        loadingLayout.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}