package com.basarcelebi.khas_app.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basarcelebi.khas_app.model.BaseModel
import com.basarcelebi.khas_app.model.HourlyForecast
import com.basarcelebi.khas_app.repositories.WeatherRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel:ViewModel(),KoinComponent {
    private val repo: WeatherRepo by inject()
    private val _hourlyForecast: MutableStateFlow<BaseModel<List<HourlyForecast>>> = MutableStateFlow(BaseModel.Loading)
    val hourlyForecast = _hourlyForecast.asStateFlow()



    fun getHourlyForecast(locationKey:String="318251"){
        viewModelScope.launch{
            repo.getHourlyForecast(locationKey).also { data->
                _hourlyForecast.update { data }
            }

        }

    }

}