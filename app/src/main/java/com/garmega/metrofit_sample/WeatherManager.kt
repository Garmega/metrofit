package com.garmega.metrofit_sample

import android.content.Context
import android.support.v4.content.LocalBroadcastManager
import com.garmega.metrofit.APIReceiver
import com.garmega.metrofit.APIResult
import com.garmega.metrofit.UINotifier

/**
 * Created by Nick on 11/7/17.
 */

// Singleton
object WeatherManager {

    init {

    }



    fun getWeather(context: Context, notifier: UINotifier) : String {

        var receiver = object : APIReceiver(notifier) {
            override fun onSuccessful(result: APIResult, context: Context) {
                super.onSuccessful(result, context)
                val freight = result.freight

            }

            override fun onPowerDown() {
                super.onPowerDown()
                LocalBroadcastManager.getInstance(context).unregisterReceiver(this)
            }
        }

        return ""
    }
}