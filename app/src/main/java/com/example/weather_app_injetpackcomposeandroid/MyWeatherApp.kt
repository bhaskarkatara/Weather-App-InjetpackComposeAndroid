package com.example.weather_app_injetpackcomposeandroid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather_app_injetpackcomposeandroid.api.NetworkResponse

@Composable
fun MyWeatherApp(modifier: Modifier,viewModel: WeatherViewModel) {
    var City by remember { mutableStateOf("") }
    val weatherResult = viewModel.weatherResult.observeAsState()


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp),
     horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = City, onValueChange =
            {City = it},
                label = {Text("Search ")}
             )
            IconButton(onClick = {viewModel.getData(City)}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        }
        when(val result = weatherResult.value){
            is NetworkResponse.Success -> {
                Text(text = result.data.toString())
            }
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }
            is NetworkResponse.Loading -> {
                CircularProgressIndicator(modifier = Modifier.wrapContentSize(Alignment.Center))
            }
            else -> {}
        }
    }

}