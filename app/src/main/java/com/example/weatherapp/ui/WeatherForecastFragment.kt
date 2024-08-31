package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.weatherapp.adapter.WeatherListAdapter
import com.example.weatherapp.databinding.FragmentWeatherForecastListBinding
import com.example.weatherapp.viewmodels.WeatherViewModel

class WeatherForecastFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var adapter: WeatherListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentWeatherForecastListBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = WeatherListAdapter(emptyList())
        binding.list.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(context, (binding.list.layoutManager as LinearLayoutManager).orientation)
        binding.list.addItemDecoration(dividerItemDecoration)

        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it.list)
        }

        return binding.root
    }
}