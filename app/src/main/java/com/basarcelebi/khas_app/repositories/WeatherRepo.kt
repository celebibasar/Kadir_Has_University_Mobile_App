package com.basarcelebi.khas_app.repositories

import com.basarcelebi.khas_app.model.BaseModel
import com.basarcelebi.khas_app.model.DailyForecasts
import com.basarcelebi.khas_app.model.HourlyForecast
import com.basarcelebi.khas_app.model.Location


interface WeatherRepo {
    suspend fun searchLocation(query: String):BaseModel<List<Location>>
    suspend fun getDailyForecast(locationKey: String): BaseModel<DailyForecasts>
    suspend fun getHourlyForecast(locationKey: String):BaseModel<List<HourlyForecast>>
}