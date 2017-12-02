package com.garmega.metrofit_sample.complex

import com.garmega.metrofit.ResponseCallback
import com.garmega.metrofit_sample.simple.UserResponse

/**
 * Created by Nick on 11/29/17.
 */

class UserManagementService {
    private val retrofitManagementService: RetrofitUserManagementService


    constructor(retrofitUserManagementService: RetrofitUserManagementService) {
        this.retrofitManagementService = retrofitUserManagementService
    }

    fun getUser(userId: Int, broadcastTag: String) {
        val call = retrofitManagementService.getUser(userId)

        val callback = object: ResponseCallback<UserResponse>(broadcastTag, "") {
            override fun performIntake(body: UserResponse?, outboundFreight: Map<String, Any>) {
                super.performIntake(body, outboundFreight)
            }
        }

        call.enqueue(callback)
    }

    fun getTeam(teamId: Int, broadcastTag: String) {
        val call = retrofitManagementService.getTeam(teamId)

        val callback = object: ResponseCallback<TeamResponse>(broadcastTag,"") {
            override fun performIntake(body: TeamResponse?, outboundFreight: Map<String, Any>) {
                super.performIntake(body, outboundFreight)
            }
        }
    }
}