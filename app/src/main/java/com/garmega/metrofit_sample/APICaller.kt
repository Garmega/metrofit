package com.garmega.metrofit_sample

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Nick on 12/1/17.
 */

object APICaller {

    val WEATHER_SERVICE: WeatherService

    init {
        WEATHER_SERVICE = WeatherService(buildNewRetrofit(RetrofitWeatherService.WEATHER_SERVICES_URL!!)
                .create(RetrofitWeatherService::class.java))
    }

    // When attached to the initialization with Retrofit
    // This intercepter will insert the following headers
    private val intercepter = Interceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
                .header("x-api-key", "")
                .header("Content-Type", "application/json")
                .header("X-Amz-Date", "")
                .method(original.method(), original.body())
                .build()

        chain.proceed(request)
    }

    private fun buildNewRetrofit(url: HttpUrl): Retrofit {
        // Takes the intercepter built in hermes and builds a HttpClient with it
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(intercepter)
        val client = httpClient.build()

        // Init Retorfit
        return Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}