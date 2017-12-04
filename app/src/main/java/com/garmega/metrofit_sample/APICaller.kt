package com.garmega.metrofit_sample

import com.garmega.metrofit_sample.complex.retrofit_interfaces.RetrofitUserManagementService
import com.garmega.metrofit_sample.complex.UserManagementService
import com.garmega.metrofit_sample.simple.retrofit_interfaces.RetrofitWeatherService
import com.garmega.metrofit_sample.simple.WeatherServiceManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Nick on 12/1/17.
 */

object APICaller {

    val USER_MANAGEMENT_SERVICE: UserManagementService
    val WEATHER_SERVICE_MANAGER: WeatherServiceManager

    init {
        USER_MANAGEMENT_SERVICE = UserManagementService(buildNewRetrofit(RetrofitUserManagementService.Companion)
                .create(RetrofitUserManagementService::class.java))

        WEATHER_SERVICE_MANAGER = WeatherServiceManager(buildNewRetrofit(RetrofitWeatherService.Companion)
                .create(RetrofitWeatherService::class.java))
    }

    private fun buildNewRetrofit(initialization: RetrofitInitialization): Retrofit {
        // Takes the intercepter built in hermes and builds a HttpClient with it
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(initialization.INTERCEPTOR)
        val client = httpClient.build()

        // Init Retorfit
        return Retrofit.Builder()
                .baseUrl(initialization.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}