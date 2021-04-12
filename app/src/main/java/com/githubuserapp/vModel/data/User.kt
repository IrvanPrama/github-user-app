package com.githubuserapp.vModel.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        var name: String? = null,
        var username: String? = null,
        var avatar: String? = null,
        var type: String? = null,
        var location: String? = null,
        var company: String? = null,
        var followers: Int = 0,
        var followings: Int = 0,
        var repository: Int = 0,
) : Parcelable