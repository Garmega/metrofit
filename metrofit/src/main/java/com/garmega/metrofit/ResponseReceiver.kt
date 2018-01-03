package com.garmega.metrofit

import android.content.Context
import android.content.Intent

/**
 * Created by Nick on 12/5/17.
 */

open class ResponseReceiver(private val notifier: UINotifier?) {

    fun onReceive(result: APIResult) {

        onIncoming()
        notifier?.let { notifier.onIncoming() }

        if (result.status === APIResult.Status.SUCCESSFUL) {

            onSuccessful(result)
            notifier?.let { notifier.onSuccessful() }

        } else if (result.status === APIResult.Status.UNSUCCESSFUL) {

            // NOTIFY DEVELOPER!@#@!$@$#%$#^%$&^%*&^(*
            onUnsuccessful(result)
            notifier?.let { notifier.onUnsuccessful() }

        } else if (result.status === APIResult.Status.INTAKE_FAILURE) {

            // NOTIFY DEVELOPER!@#@!$@$#%$#^%$&^%*&^(*
            onIntakeFailure(result)
            notifier?.let { notifier.onIntakeFailure() }

        } else if (result.status === APIResult.Status.FAILED_TO_REACH_SERVER) {

            onFailedToReachServer(result)
            notifier?.let { notifier.onFailedToReachServer() }

        }

        onPowerDown()
        notifier?.let { notifier.onPowerDown() }
    }


    //------------------------------------------------------------------
    //   Handle Functions
    //------------------------------------------------------------------

    /**
     * Handle method to respond BEFORE any any status specific case. THIS WILL ALWAYS BE CALLED.
     */
    open fun onIncoming() {}

    /**
     * Handle method for case where `APIResult` has status `Status.SUCCESSFUL`
     * @param result
     * @param context
     */
    open fun onSuccessful(result: APIResult) {}

    /**
     * Handle method for case where `APIResult` has status `Status.UNSUCCESSFUL`
     * @param result
     * @param context
     */
    open fun onUnsuccessful(result: APIResult) {}

    /**
     * Handle method for case where `APIResult` has status `Status.INTAKE_FAILURE`
     * @param result
     * @param context
     */
    open fun onIntakeFailure(result: APIResult) {}

    /**
     * Handle method for case where `APIResult` has status `Status.FAILED_TO_REACH_SERVER`
     * @param result
     * @param context
     */
    open fun onFailedToReachServer(result: APIResult) {}

    /**
     * Handle method to respond AFTER any any status specific case. THIS WILL ALWAYS BE CALLED.
     */
    open fun onPowerDown() {}
}