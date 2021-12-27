package com.app_meta.network.model


data class Item(
    val name: String,
    val description: String,
    val forks_count: Int,
    val stargazers_count: Int,
    var owner: Author
)
