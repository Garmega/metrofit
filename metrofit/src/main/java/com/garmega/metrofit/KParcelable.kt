package com.garmega.metrofit

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Nick on 11/27/17.
 */

interface KParcelable : Parcelable {
    // 99.999% time returning 0 anyway.
    override fun describeContents() = 0

    // Parcel is never null.
    override fun writeToParcel(dest: Parcel, flags: Int)
}