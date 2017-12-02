package com.garmega.metrofit_sample

import android.util.Log
import com.garmega.metrofit.ResponseCallback
import com.garmega.metrofit.UINotifier
import com.google.gson.internal.LinkedTreeMap
import okhttp3.HttpUrl
import retrofit2.Call
import retrofit2.Callback

/**
 * Created by Nick on 11/29/17.
 */

class WeatherService {
    val RETROFIT_SERVICE: RetrofitWeatherService


    constructor(retrofitWeatherService: RetrofitWeatherService) {
        this.RETROFIT_SERVICE = retrofitWeatherService
    }

    fun getWeather(cityId: Int) {
        val call = RETROFIT_SERVICE.getWeather(cityId)

        val callback = object: ResponseCallback<LinkedTreeMap<*,*>>("", "") {
            override fun performIntake(body: LinkedTreeMap<*, *>?, outboundFreight: Map<String, Any>) {
                super.performIntake(body, outboundFreight)
            }
        }
    }
}