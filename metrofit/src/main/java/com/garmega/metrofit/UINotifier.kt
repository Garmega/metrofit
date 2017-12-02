package com.garmega.metrofit

/**
 * Created by Nick on 11/7/17.
 */

abstract class UINotifier {
    open fun onIncoming() {}

    open fun onSuccessful() {}

    open fun onUnsuccessful() {}

    open fun onIntakeFailure() {}

    open fun onFailedToReachServer() {}

    open fun onPowerDown() {}
}