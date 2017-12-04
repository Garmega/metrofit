package com.garmega.metrofit_sample.complex

import com.garmega.metrofit_sample.RetrofitInitialization
import com.garmega.metrofit_sample.simple.UserResponse
import okhttp3.HttpUrl
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Nick on 11/29/17.
 */

interface RetrofitUserManagementService {
    companion object: RetrofitInitialization {
        override val INTERCEPTOR: Interceptor
            get() = Interceptor { chain ->
                val original = chain.request()

                val request = original.newBuilder()
                        .header("x-api-key", "")
                        .header("Content-Type", "application/json")
                        .header("X-Amz-Date", "")
                        .method(original.method(), original.body())
                        .build()

                chain.proceed(request)
            }

        override val URL: HttpUrl?
            get() = HttpUrl.parse("https://6rcmh8l5f0.execute-api.us-east-1.amazonaws.com/")
    }

    @GET("user/{userId}")
    fun getUser(@Path("userId") userId: String): Call<UserResponse>

    @GET("team/{teamId}")
    fun getTeam(@Path("teamID") teamId: String): Call<TeamResponse>
}