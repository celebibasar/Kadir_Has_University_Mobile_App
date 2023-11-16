package com.basarcelebi.khas_app.network

import com.basarcelebi.khas_app.model.DailyForecasts
import com.basarcelebi.khas_app.model.HourlyForecast
import com.basarcelebi.khas_app.model.Location

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val APIKEY = "87GySTG5JKyE9ztfFxUY2zjpAdzADHsU"
interface Api {
    @GET("locations/v1/cities/search")
    suspend fun searchLocation(
        @Query("apikey") apiKey:String = APIKEY,
        @Query("q") query:String
    ):Response<List<Location>>

    @GET("forecasts/v1/daily/5day/318251")
    suspend fun getDailyForecasts(
        @Path("318251") locationKey:String,
        @Query("apikey") apiKey:String = APIKEY,
        @Query("metric") metric:Boolean = true
    ):Response<DailyForecasts>

    @GET("forecasts/v1/hourly/12hour/318251")
    suspend fun getHourlyForecasts(
        @Path("318251") locationKey:String,
        @Query("apikey") apiKey:String = APIKEY,
        @Query("metric") metric:Boolean = true
    ):Response<List<HourlyForecast>>
}