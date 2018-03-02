package com.garmega.metrofit_sample.complex

import com.garmega.metrofit.ResponseCallback
import com.garmega.metrofit.ResponseReceiver
import com.garmega.metrofit_sample.complex.response_models.TeamResponse
import com.garmega.metrofit_sample.complex.response_models.UserResponse
import com.garmega.metrofit_sample.complex.retrofit_interfaces.RetrofitUserManagementService
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Nick on 11/29/17.
 */

class UserManagementService(private val retrofitUserManagementService: RetrofitUserManagementService) {

    fun getUser(userId: String, receiver: ResponseReceiver) {
        val call = retrofitUserManagementService.getUser(userId)

        val callback = object: ResponseCallback<UserResponse>(receiver) {
            override fun performIntake(body: UserResponse?, outboundFreight: Map<String, Any>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCallFailure(call: Call<UserResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onUnsuccessfulResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        call.enqueue(callback)
    }

    fun getTeam(teamId: String, receiver: ResponseReceiver) {
        val call = retrofitUserManagementService.getTeam(teamId)

        val callback = object: ResponseCallback<TeamResponse>(receiver) {
            override fun performIntake(body: TeamResponse?, outboundFreight: Map<String, Any>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCallFailure(call: Call<TeamResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onUnsuccessfulResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        call.enqueue(callback)
    }
}