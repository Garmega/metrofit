package com.garmega.metrofit_sample.simple

import com.garmega.metrofit_sample.complex.TeamResponse
import okhttp3.HttpUrl
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Nick on 12/2/17.
 */

interface RetrofitWeatherService {
    companion object {
        val WEATHER_SERVICES_URL = HttpUrl.parse("https://6rcmh8l5f0.execute-api.us-east-1.amazonaws.com/")
    }

    @GET("user/{cityId}")
    fun getWeather(@Path("cityId") cityId: String): Call<WeatherDataResponse>
}