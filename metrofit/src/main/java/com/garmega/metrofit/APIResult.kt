package com.garmega.metrofit

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Nick on 11/7/17.
 */

/*
TODO: Freight needs to be flattened differently or manually.
Using Serializable is slow and potentially dangerous
https://www.3pillarglobal.com/insights/parcelable-vs-java-serialization-in-android-app-development

TODO: Internal Status object should also be flattened differently but its a small object so not a priority

TODO: Finish and test Parcelable implementation
https://medium.com/@BladeCoder/reducing-parcelable-boilerplate-code-using-kotlin-741c3124a49a

TODO: Properly hide freight interaction
 */

class APIResult: KParcelable {

    var freight: HashMap<String, Any>
    var status: Status

    var httpStatusCode: Int? = null
    var message: String? = null
    var throwable: Throwable? = null

    val summary: String
        get() = ("Code: " + httpStatusCode + " | Message: " + message
                + " | Freight: " + freight!!.toString())

    //------------------------------------------------------------------
    //   Constructors
    //------------------------------------------------------------------

    constructor(freight: HashMap<String, Any>) {
        this.freight = freight
        this.status = Status.NOT_SENT
    }

    constructor() : this(HashMap())

    //------------------------------------------------------------------
    //   KParcelable Overrides
    //------------------------------------------------------------------

    constructor(parcel: Parcel) {
        this.freight = parcel.readSerializable() as HashMap<String, Any>
        this.status = parcel.readSerializable() as Status

        this.httpStatusCode = parcel.readInt()
        this.message = parcel.readString()
        this.throwable = parcel.readSerializable() as Throwable
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeSerializable(freight)
        dest.writeSerializable(status)

        // Semantic note: Android is ok with putting in null and taking it back out
        dest.writeInt(httpStatusCode!!)
        dest.writeString(message!!)
        dest.writeSerializable(throwable!!)
    }

    //------------------------------------------------------------------
    //   Companion
    //------------------------------------------------------------------

    companion object {
        // For KParcelable
        @JvmField
        val CREATOR = object : Parcelable.Creator<APIResult> {
            override fun createFromParcel(p0: Parcel?): APIResult {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun newArray(p0: Int): Array<APIResult> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }

    //------------------------------------------------------------------
    //   Private class
    //------------------------------------------------------------------

    enum class Status {
        NOT_SENT,
        PENDING,

        // 200 - 300
        SUCCESSFUL,
        // As long as got a response and NOT successful
        UNSUCCESSFUL,

        // Received a response but something we did locally failed
        INTAKE_FAILURE,
        // Call failed to reach the server.
        FAILED_TO_REACH_SERVER
    }
}