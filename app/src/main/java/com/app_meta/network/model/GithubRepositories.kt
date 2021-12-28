package com.app_meta.network.model

import com.google.gson.annotations.SerializedName

data class GithubRepositories(
    @SerializedName("items") val items: List<Item>)
