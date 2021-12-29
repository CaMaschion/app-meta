package com.app_meta.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("owner") var owner: Author
) : Parcelable {
    constructor(repositoryEntity: RepositoryEntity) : this (
        name = repositoryEntity.name,
        description = repositoryEntity.description,
        stargazersCount = repositoryEntity.stargazersCount,
        forksCount = repositoryEntity.stargazersCount,
        owner = Author (
            login = repositoryEntity.owner,
            avatarUrl = repositoryEntity.avatar
        )
    )
}
