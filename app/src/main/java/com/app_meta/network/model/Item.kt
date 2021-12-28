package com.app_meta.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("forks_count") val forks_count: Int,
    @SerializedName("stargazers_count") val stargazers_count: Int,
    @SerializedName("owner") var owner: Author
) : Parcelable
