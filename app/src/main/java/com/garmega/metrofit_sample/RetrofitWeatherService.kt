package com.garmega.metrofit_sample

import com.google.gson.internal.LinkedTreeMap
import okhttp3.HttpUrl
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.LinkedHashMap

/**
 * Created by Nick on 11/29/17.
 */

interface RetrofitWeatherService {
    companion object {
        val WEATHER_SERVICES_URL = HttpUrl.parse("https://6rcmh8l5f0.execute-api.us-east-1.amazonaws.com/")
    }

    @GET("announcements/{announcementId}")
    fun getWeather(@Path("announcementId") cityId: Int): Call<LinkedTreeMap<*, *>>
}