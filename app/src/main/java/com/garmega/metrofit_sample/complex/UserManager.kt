package com.garmega.metrofit_sample.complex

import com.garmega.metrofit.APIResult
import com.garmega.metrofit.ResponseReceiver
import com.garmega.metrofit_sample.APICaller

/**
 * Created by Nick on 11/7/17.
 */

// Singleton
object UserManager {

    fun getUser(userId: String, receiver: ResponseReceiver) {

        val childReceiver = object : ResponseReceiver() {
            override fun onSuccessful(result: APIResult) {
                super.onSuccessful(result)
            }
        }

        receiver.setChildReceiver(childReceiver)

        APICaller.USER_MANAGEMENT_SERVICE.getUser(userId, receiver)

    }
}