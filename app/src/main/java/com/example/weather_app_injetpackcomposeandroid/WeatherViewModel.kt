package com.example.weather_app_injetpackcomposeandroid

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app_injetpackcomposeandroid.api.Constant
import com.example.weather_app_injetpackcomposeandroid.api.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel :ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
     fun getData(City:String){
         viewModelScope.launch {
            val resposne =  weatherApi.getWeatherData(Constant.apikey,City)
             if(resposne.isSuccessful){
                 Log.i(TAG, "getData: ${resposne.body().toString()}")
             }else{
             Log.i(TAG, "getData: ${resposne.message()}")
             }
         }

    }
}