package com.garmega.metrofit

/**
 * Created by Nick on 12/5/17.
 *
 * Standardized receiver object that handles our custom APIResult packaging.
 * Overrides allow implementers to respond to specific cases of the APIResult status.
 *
 * This receiver can accept a child receiver of the same type to allow handling
 * to the nth layer.
 *
 * TODO URGENT: Not sure if the receivers are chained correctly.
 * If the parent is responsible of owning the child
 * then as the chain gets longer, the 0th parent still needs to be referenced to be put
 * into the final callback otherwise chaining doesn't happen. Perhaps relationship needs to be
 * reverse? Investigate.
 *
 */

open class ResponseReceiver {
    private val TAG = "RESPONSE_RECEIVER"
    private var childReceiver: ResponseReceiver? = null

    fun receiveResponse(result: APIResult) {

        onIncoming()
        childReceiver?.let { childReceiver -> childReceiver.onIncoming() }

        if (result.status === APIResult.Status.SUCCESSFUL) {

            onSuccessful(result)
            childReceiver?.let { childReceiver -> childReceiver.onSuccessful(result) }

        } else if (result.status === APIResult.Status.UNSUCCESSFUL) {

            // NOTIFY DEVELOPER!@#@!$@$#%$#^%$&^%*&^(*
            onUnsuccessful(result)
            childReceiver?.let { childReceiver -> childReceiver.onUnsuccessful(result) }

        } else if (result.status === APIResult.Status.INTAKE_FAILURE) {

            // NOTIFY DEVELOPER!@#@!$@$#%$#^%$&^%*&^(*
            onIntakeFailure(result)
            childReceiver?.let { childReceiver -> childReceiver.onIntakeFailure(result) }

        } else if (result.status === APIResult.Status.FAILED_TO_REACH_SERVER) {

            onFailedToReachServer(result)
            childReceiver?.let { childReceiver -> childReceiver.onFailedToReachServer(result) }

        }

        onDestroy()
        childReceiver?.let { childReceiver -> childReceiver.onDestroy() }
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
    open fun onDestroy() {}

    //------------------------------------------------------------------
    //   Child ResponseReceiver Functions
    //------------------------------------------------------------------

    /**
     * Checks if this receiver has a child receiver attached to it
     */
    fun hasChildReceiver(): Boolean {
        return this.childReceiver != null
    }

    /**
     * Attempts to set the a child receiver to this receiver.
     *
     * Throws an IllegalStateException if this receiver already has a child.
     * TODO: Probably should handle this more elegantly than just throwing an exception.
     */
    fun setChildReceiver(childReceiver: ResponseReceiver) {
        if (this.childReceiver == null) {
            this.childReceiver = childReceiver
        } else {
            throw IllegalStateException("This ResponseReceiver already has a child receiver")
        }
    }
}