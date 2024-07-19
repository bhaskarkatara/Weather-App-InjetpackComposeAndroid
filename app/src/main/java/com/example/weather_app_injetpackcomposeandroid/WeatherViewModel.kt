package com.example.weather_app_injetpackcomposeandroid

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app_injetpackcomposeandroid.api.Constant
import com.example.weather_app_injetpackcomposeandroid.api.NetworkResponse
import com.example.weather_app_injetpackcomposeandroid.api.RetrofitInstance
import com.example.weather_app_injetpackcomposeandroid.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel :ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
     val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

     fun getData(City:String){
         _weatherResult.value = NetworkResponse.Loading
         viewModelScope.launch {
            try {
                val response =  weatherApi.getWeatherData(Constant.apikey,City)
                if(response.isSuccessful){
                    response.body()?.let{
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            }
            catch (e:Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }
         }

    }
}