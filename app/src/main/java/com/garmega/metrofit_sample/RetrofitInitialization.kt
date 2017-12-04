package com.garmega.metrofit_sample

import okhttp3.HttpUrl
import okhttp3.Interceptor
import retrofit2.Retrofit

/**
 * Created by Nick on 12/3/17.
 */

interface RetrofitInitialization {
    val URL: HttpUrl?
    val INTERCEPTOR: Interceptor
}