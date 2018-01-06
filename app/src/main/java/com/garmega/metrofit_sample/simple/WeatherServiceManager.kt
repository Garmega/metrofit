package com.garmega.metrofit_sample.simple

import android.content.Context
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import com.garmega.metrofit.*
import com.garmega.metrofit_sample.simple.response_models.WeatherDataResponse
import com.garmega.metrofit_sample.simple.retrofit_interfaces.RetrofitWeatherService
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Nick on 12/2/17.
 *
 * TODO: Obfuscate and renew APIKey.
 * TODO: Start processing and displaying received weather information.
 *
 * TODO: Cleanup global namespace by moving managers into APICaller.
 */

class WeatherServiceManager(private val retrofitWeatherService: RetrofitWeatherService) {

    /*
    This method handles calling and converting data into local objects. Do not modify local model here
     */
    fun getWeather(cityName: String, receiver: ResponseReceiver) {
        val call = retrofitWeatherService.getWeather(cityName, "52999c99f1a294b470e1e57f2602a5e1")

        val callback = object: ResponseCallback<WeatherDataResponse>(receiver, false, "GET_WEATHER") {
            override fun performIntake(body: WeatherDataResponse?, outboundFreight: Map<String, Any>) {
                super.performIntake(body, outboundFreight)
            }

            override fun onUnsuccessfulResponse(call: Call<WeatherDataResponse>, response: Response<WeatherDataResponse>) {
                super.onUnsuccessfulResponse(call, response)
            }

            override fun onCallFailure(call: Call<WeatherDataResponse>, t: Throwable) {
                super.onCallFailure(call, t)
            }

            override fun onFailure(call: Call<WeatherDataResponse>, t: Throwable) {
                super.onFailure(call, t)
            }
        }

        call.enqueue(callback)
    }
}