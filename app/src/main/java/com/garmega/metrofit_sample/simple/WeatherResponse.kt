package com.garmega.metrofit_sample.simple

/**
 * Created by Nick on 12/1/17.
 */

class UserResponse(val data: WeatherDataResponse)

class WeatherDataResponse(
        val main: WeatherMainData
)

class WeatherMainData (
        val temp: Double,
        val pressure: Double,
        val humidity: Double,
        val temp_min: Double,
        val temp_max: Double
)

