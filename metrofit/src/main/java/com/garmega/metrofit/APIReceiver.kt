package com.garmega.metrofit

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by Nick on 11/7/17.
 */
/**
 * Standardized BroadcastReceiver that handles our custom APIResult packaging.
 * Overrides allow implementers to respond to specific cases of the APIResult status.
 *
 * This receiver is meant to live after the layer where any ExposedService
 * has finished communicating with the server and converted objects into local types.
 *
 * A `UINotifer` can be taken (not required) in order for UIs to respond to each
 * event as well.
 */
open class APIReceiver(private val notifier: UINotifier?) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        onIncoming()
        notifier?.let { notifier.onIncoming() }

        val result = intent.getParcelableExtra<APIResult>("result")

        if (result.status === APIResult.Status.SUCCESSFUL) {

            onSuccessful(result, context)
            notifier?.let { notifier.onSuccessful() }

        } else if (result.status === APIResult.Status.UNSUCCESSFUL) {

            // NOTIFY DEVELOPER!@#@!$@$#%$#^%$&^%*&^(*
            onUnsuccessful(result, context)
            notifier?.let { notifier.onUnsuccessful() }

        } else if (result.status === APIResult.Status.INTAKE_FAILURE) {

            // NOTIFY DEVELOPER!@#@!$@$#%$#^%$&^%*&^(*
            onIntakeFailure(result, context)
            notifier?.let { notifier.onIntakeFailure() }

        } else if (result.status === APIResult.Status.FAILED_TO_REACH_SERVER) {

            onFailedToReachServer(result, context)
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
    open fun onSuccessful(result: APIResult, context: Context) {}

    /**
     * Handle method for case where `APIResult` has status `Status.UNSUCCESSFUL`
     * @param result
     * @param context
     */
    open fun onUnsuccessful(result: APIResult, context: Context) {}

    /**
     * Handle method for case where `APIResult` has status `Status.INTAKE_FAILURE`
     * @param result
     * @param context
     */
    open fun onIntakeFailure(result: APIResult, context: Context) {}

    /**
     * Handle method for case where `APIResult` has status `Status.FAILED_TO_REACH_SERVER`
     * @param result
     * @param context
     */
    open fun onFailedToReachServer(result: APIResult, context: Context) {}

    /**
     * Handle method to respond AFTER any any status specific case. THIS WILL ALWAYS BE CALLED.
     */
    open fun onPowerDown() {}
}