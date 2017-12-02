package com.garmega.metrofit_sample.complex

import com.garmega.metrofit.ResponseCallback
import com.garmega.metrofit_sample.simple.UserResponse

/**
 * Created by Nick on 11/29/17.
 */

class UserService {
    private val retrofitService: RetrofitUserService


    constructor(retrofitUserService: RetrofitUserService) {
        this.retrofitService = retrofitUserService
    }

    fun getUser(userId: Int, broadcastTag: String) {
        val call = retrofitService.getUser(userId)

        val callback = object: ResponseCallback<UserResponse>(broadcastTag, "") {
            override fun performIntake(body: UserResponse?, outboundFreight: Map<String, Any>) {
                super.performIntake(body, outboundFreight)
            }
        }

        call.enqueue(callback)
    }
}