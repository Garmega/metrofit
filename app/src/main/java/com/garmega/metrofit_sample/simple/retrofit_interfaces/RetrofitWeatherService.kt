package com.garmega.metrofit_sample.simple.retrofit_interfaces

import android.util.Log
import com.garmega.metrofit_sample.RetrofitInitialization
import com.garmega.metrofit_sample.simple.response_models.WeatherDataResponse
import okhttp3.HttpUrl
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Nick on 12/2/17.
 */

interface RetrofitWeatherService {
    companion object: RetrofitInitialization {
        override val INTERCEPTOR: Interceptor
            get() = Interceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build()

            chain.proceed(request)
        }

        override val URL: HttpUrl?
            get() = HttpUrl.parse("https://api.openweathermap.org/data/2.5/")
    }

    @GET("weather")
    fun getWeather(
            @Query("q") cityName: String,
            @Query("APPID") key: String): Call<WeatherDataResponse>
}