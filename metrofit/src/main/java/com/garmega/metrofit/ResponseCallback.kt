package com.garmega.metrofit

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

/**
 * Created by Nick on 11/7/17.
 */

/*
TODO: Figure out how to broadcast of within the context of a library

TODO: Clean up all references and interactions with APIResult.freight to be safer and more consistent

TODO: Comment more stuff
 */

/**
 * Standardized Retrofit2 Callback object that utilizes our custom `APIResult`
 * packaging rules.
 *
 * This callback is to allows us to intercept the response, interpret and convert raw data
 * into our local object types before sending off the result to
 * an `APIReceiver` to do with what they please.
 *
 * In short, callbacks based on this class should NOT mutate our local data stores.
 *
 * @param <T> Where T is any object type.
</T> */
class ResponseCallback<T>(broadcastTag: String, private val debugTag: String) : Callback<T> {
    private val broadcastIntent: Intent
    private val result = APIResult()
    private val freight = HashMap<String, Any>()

    init {
        this.broadcastIntent = Intent(broadcastTag)
    }

    //------------------------------------------------------------------
    //   Override Functions
    //------------------------------------------------------------------

    override fun onResponse(call: Call<T>, response: Response<T>) {
        result.httpStatusCode = response.code()
        freight.put("originalCall", call)
        Log.i("APICALL", debugTag + " | Request : " + call.request().toString())

        // Response is successful if it returned a status code of 200-300.
        if (response.isSuccessful) {
            // Give the caller a chance to process data with 'performIntake' hook
            try {
                val body = response.body()
                performIntake(body, freight)

                // Caller 'performIntake' executed without any exceptions. Mark result as such.
                result.status = APIResult.Status.SUCCESSFUL
                Log.i("APICALL", debugTag + " | onResponse : INTAKE_SUCCESS")

            } catch (e: Exception) {
                // The caller did something illegal. Catch and package exception
                result.status = APIResult.Status.INTAKE_FAILURE
                result.throwable = e

                val intakeFailureLog = debugTag + " | onResponse : INTAKE_FAILURE"
                val stackTrace = Log.getStackTraceString(e)

                //errorReport(new Exception(intakeFailureLog), call, response);

                Log.e("APICALL", intakeFailureLog)
                Log.e("APICALL", stackTrace)
            }

        // Response was unsuccessful
        } else {
            result.status = APIResult.Status.UNSUCCESSFUL

            result.message = response.message()

            val unSuccessfulLog = debugTag + " | onUnsuccessful - Code: " +
                    response.code() + " | Message: " + response.message()

            //errorReport(new Exception(unSuccessfulLog), call, response);

            Log.e("APICALL", unSuccessfulLog)

            onUnsuccessfulResponse(call, response)
        }

        // Broadcast result back to original caller
        broadcastResult(call)
    }

    fun errorReport(exception: Exception, call: Call<T>, response: Response<T>) {
        if (response.body() != null) {

        } else if (call.request().body() != null) {

        }

    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.e("APICALL", debugTag + " | onFailure - Throwable: " + t.localizedMessage)
        result.status = APIResult.Status.FAILED_TO_REACH_SERVER
        result.throwable = t

        onCallFailure(call, t)

        broadcastResult(call)
    }

    //------------------------------------------------------------------
    //   Private Functions
    //------------------------------------------------------------------

    private fun broadcastResult(call: Call<*>) {
        result.freight = freight
        broadcastIntent.putExtra("result", result)
        // Figure out how to broadcast out of within the context of a library
//        LocalBroadcastManager.getInstance(TackApp.getInstance()).sendBroadcast(broadcastIntent)
    }

    //------------------------------------------------------------------
    //   Handle Functions
    //------------------------------------------------------------------

    fun performIntake(body: T?, outboundFreight: Map<String, Any>) {}

    fun onUnsuccessfulResponse(call: Call<T>, response: Response<T>) {}

    fun onCallFailure(call: Call<T>, t: Throwable) {}
}