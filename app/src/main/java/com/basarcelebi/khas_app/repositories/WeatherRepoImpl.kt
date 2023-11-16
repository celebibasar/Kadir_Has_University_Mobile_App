package com.basarcelebi.khas_app.repositories

import com.basarcelebi.khas_app.model.BaseModel
import com.basarcelebi.khas_app.model.DailyForecasts
import com.basarcelebi.khas_app.model.HourlyForecast
import com.basarcelebi.khas_app.model.Location
import com.basarcelebi.khas_app.network.Api

import retrofit2.Response

class WeatherRepoImpl(private val api: Api):WeatherRepo {
    override suspend fun searchLocation(query: String): BaseModel<List<Location>> {
        return request {
            api.searchLocation(query = query)
        }
    }

    override suspend fun getDailyForecast(locationKey: String): BaseModel<DailyForecasts> {
        return request {
            api.getDailyForecasts(locationKey = locationKey)
        }
    }

    override suspend fun getHourlyForecast(locationKey: String): BaseModel<List<HourlyForecast>> {
        return request {
            api.getHourlyForecasts(locationKey = locationKey)
        }
    }
}

suspend fun<T>request(request:suspend ()-> Response<T>):BaseModel<T>
{
    try {
        request().also {
            return if(it.isSuccessful){
                BaseModel.Success(it.body()!!)
            }else{
                BaseModel.Error(it.errorBody()?.string().toString())
            }
        }
    }catch (e:Exception){
        return BaseModel.Error(e.message.toString())
    }
}