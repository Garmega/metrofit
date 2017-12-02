package com.garmega.metrofit_sample.simple

import android.content.Context
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import com.garmega.metrofit.APIReceiver
import com.garmega.metrofit.APIResult
import com.garmega.metrofit.ResponseCallback
import com.garmega.metrofit.UINotifier
import com.garmega.metrofit_sample.APICaller
import com.garmega.metrofit_sample.complex.RetrofitUserManagementService

/**
 * Created by Nick on 12/2/17.
 */

class WeatherService(private val retrofitWeatherService: RetrofitWeatherService) {

    /*
    This method handles model after conversion happens. This is where you should modify your local model
     */
    fun getWeather(cityId: String, context: Context, notifier: UINotifier) {
        val filter = IntentFilter("TEST")

        val getWeatherReceiver = object: APIReceiver(notifier) {
            override fun onSuccessful(result: APIResult, context: Context) {
                super.onSuccessful(result, context)
            }

            override fun onPowerDown() {
                super.onPowerDown()
                LocalBroadcastManager.getInstance(context).unregisterReceiver(this)
            }
        }

        LocalBroadcastManager.getInstance(context).registerReceiver(getWeatherReceiver, filter)
        getWeather(cityId, "TEST")
    }

    /*
    This method handles calling and converting data into local objects. Do not modify local model here
     */
    private fun getWeather(cityId: String, broadcastTag: String) {
        val call = retrofitWeatherService.getWeather(cityId)

        val callback = object: ResponseCallback<WeatherDataResponse>(broadcastTag, "") {
            override fun performIntake(body: WeatherDataResponse?, outboundFreight: Map<String, Any>) {
                super.performIntake(body, outboundFreight)
            }
        }

        call.enqueue(callback)
    }
}