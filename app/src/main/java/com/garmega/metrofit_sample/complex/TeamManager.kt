package com.garmega.metrofit_sample.complex

import android.content.Context
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import com.garmega.metrofit.APIReceiver
import com.garmega.metrofit.APIResult
import com.garmega.metrofit.ResponseReceiver
import com.garmega.metrofit.UINotifier
import com.garmega.metrofit_sample.APICaller

/**
 * Created by Nick on 12/2/17.
 */

// Singleton
object TeamManager {

    init {

    }

    fun getTeam(teamId: String, context: Context, notifier: UINotifier) {
        val filter = IntentFilter("TEST")
        val getUserReceiver = object : ResponseReceiver(notifier) {


            override fun onSuccessful(result: APIResult) {
                super.onSuccessful(result)
                val freight = result.freight
            }
        }

        APICaller.USER_MANAGEMENT_SERVICE.getTeam(teamId, getUserReceiver)
    }
}