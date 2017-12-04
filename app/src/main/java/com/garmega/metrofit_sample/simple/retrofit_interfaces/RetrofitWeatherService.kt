package com.garmega.metrofit_sample.simple.retrofit_interfaces

import com.garmega.metrofit_sample.RetrofitInitialization
import com.garmega.metrofit_sample.simple.response_models.WeatherDataResponse
import okhttp3.HttpUrl
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Nick on 12/2/17.
 */

interface RetrofitWeatherService {
    companion object: RetrofitInitialization {
        override val INTERCEPTOR: Interceptor
            get() = Interceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                    .header("x-api-key", "")
                    .header("Content-Type", "application/json")
                    .header("X-Amz-Date", "")
                    .method(original.method(), original.body())
                    .build()

            chain.proceed(request)
        }

        override val URL: HttpUrl?
            get() = HttpUrl.parse("https://6rcmh8l5f0.execute-api.us-east-1.amazonaws.com/")
    }

    @GET("user/{cityId}")
    fun getWeather(@Path("cityId") cityId: String): Call<WeatherDataResponse>
}