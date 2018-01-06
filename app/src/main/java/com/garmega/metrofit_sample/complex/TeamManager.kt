package com.garmega.metrofit_sample.complex

import com.garmega.metrofit.APIResult
import com.garmega.metrofit.ResponseReceiver
import com.garmega.metrofit_sample.APICaller

/**
 * Created by Nick on 12/2/17.
 */

// Singleton
object TeamManager {

    fun getTeam(teamId: String, receiver: ResponseReceiver) {
        val childReceiver = object : ResponseReceiver() {

            override fun onSuccessful(result: APIResult) {
                super.onSuccessful(result)
            }
        }

        receiver.setChildReceiver(childReceiver)

        APICaller.USER_MANAGEMENT_SERVICE.getTeam(teamId, receiver)
    }
}