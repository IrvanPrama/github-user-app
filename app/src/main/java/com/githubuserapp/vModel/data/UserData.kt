package com.githubuserapp.vModel.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData (
    var username: String? = null,
    var name: String? = null,
    var avatar: String? = null,
    var type: String? = null,
    var followings: Int = 0,
    var followers: Int = 0,
    var repository: Int = 0,
    var company: String? = null,
    var location: String? = null,
): Parcelable