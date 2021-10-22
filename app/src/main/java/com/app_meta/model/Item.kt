package com.app_meta.model

import java.security.acl.Owner

data class Item(
    val name: String,
    val description: String,
    val forks_count: Int,
    val stargazers_count: Int,
    var owner: Owner
)
