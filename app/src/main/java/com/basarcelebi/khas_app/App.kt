package com.basarcelebi.khas_app

import android.app.Application
import com.basarcelebi.khas_app.network.Api
import com.basarcelebi.khas_app.network.HeaderInterceptor
import com.basarcelebi.khas_app.repositories.WeatherRepo
import com.basarcelebi.khas_app.repositories.WeatherRepoImpl
import okhttp3.OkHttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(module {
                single {
                    val client = OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
                        .build()
                    Retrofit
                        .Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .baseUrl("http://dataservice.accuweather.com/")
                        .build()
                }
                single {
                    val retrofit: Retrofit =get()
                    retrofit.create(Api::class.java)
                }
                single {
                    val api:Api = get()
                    WeatherRepoImpl(api)
                } bind WeatherRepo::class
            })
        }
    }
}