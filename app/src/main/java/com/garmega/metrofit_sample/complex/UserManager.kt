package com.garmega.metrofit_sample.complex

import android.content.Context
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import com.garmega.metrofit.APIReceiver
import com.garmega.metrofit.APIResult
import com.garmega.metrofit.UINotifier
import com.garmega.metrofit_sample.APICaller

/**
 * Created by Nick on 11/7/17.
 */

// Singleton
object UserManager {

    init {

    }

    fun getUser(userId: String, context: Context, notifier: UINotifier) {
        val filter = IntentFilter("TEST")
        val getUserReceiver = object : APIReceiver(notifier) {


            override fun onSuccessful(result: APIResult, context: Context) {
                super.onSuccessful(result, context)
                val freight = result.freight

            }

            override fun onPowerDown() {
                super.onPowerDown()
                LocalBroadcastManager.getInstance(context).unregisterReceiver(this)
            }
        }

        LocalBroadcastManager.getInstance(context).registerReceiver(getUserReceiver, filter)
        APICaller.USER_MANAGEMENT_SERVICE.getUser(userId, "TEST")

    }
}