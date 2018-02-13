package com.juawapps.newsspread.categories

import android.os.Parcel
import android.os.Parcelable

/**
 * Class holding a news category object.
 */

class NewsCategory(val key: String, val label: Int) : Parcelable {

    private constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(key)
        parcel.writeInt(label)
    }

    override fun describeContents() = 0

    companion object {

        @JvmField
        val CREATOR = object : Parcelable.Creator<NewsCategory> {
            override fun createFromParcel(parcel: Parcel) = NewsCategory(parcel)

            override fun newArray(size: Int): Array<NewsCategory?> = arrayOfNulls(size)
        }
    }
}
