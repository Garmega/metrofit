package com.garmega.metrofit_sample.complex

import com.garmega.metrofit.ResponseCallback
import com.garmega.metrofit_sample.simple.UserResponse

/**
 * Created by Nick on 11/29/17.
 */

class UserManagementService(private val retrofitUserManagementService: RetrofitUserManagementService) {

    fun getUser(userId: String, broadcastTag: String) {
        val call = retrofitUserManagementService.getUser(userId)

        val callback = object: ResponseCallback<UserResponse>(broadcastTag, "") {
            override fun performIntake(body: UserResponse?, outboundFreight: Map<String, Any>) {
                super.performIntake(body, outboundFreight)
            }
        }

        call.enqueue(callback)
    }

    fun getTeam(teamId: String, broadcastTag: String) {
        val call = retrofitUserManagementService.getTeam(teamId)

        val callback = object: ResponseCallback<TeamResponse>(broadcastTag,"") {
            override fun performIntake(body: TeamResponse?, outboundFreight: Map<String, Any>) {
                super.performIntake(body, outboundFreight)
            }
        }
    }
}