package com.app_meta.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
) : Parcelable
