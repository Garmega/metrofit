package com.garmega.metrofit_sample.complex

import com.garmega.metrofit_sample.simple.UserResponse
import okhttp3.HttpUrl
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Nick on 11/29/17.
 */

interface RetrofitUserService {
    companion object {
        val USER_SERVICES_URL = HttpUrl.parse("https://6rcmh8l5f0.execute-api.us-east-1.amazonaws.com/")
    }

    @GET("announcements/{announcementId}")
    fun getUser(@Path("announcementId") userId: Int): Call<UserResponse>
}