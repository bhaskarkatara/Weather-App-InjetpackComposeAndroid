package com.example.weather_app_injetpackcomposeandroid.api

// T refers to WeatherModel,generic class
sealed class NetworkResponse<out  T>{
  data class Success<out T>(val data : T) : NetworkResponse<T>()
   data class Error(val message : String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()

}