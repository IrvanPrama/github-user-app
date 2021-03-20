package com.githubuserapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
        var photo: Int = 0,
        var name: String = "",
        var username: String = "",
        var repository: String = "",
        var following: String = "",
        var follower: String = "",
        var company: String = "",
        var location: String = ""
        ): Parcelable
