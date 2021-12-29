package com.app_meta.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_repositories")
class RepositoryEntity(
    @PrimaryKey
    val name: String,
    val avatar: String,
    val owner: String,
    val description: String?,
    val stargazersCount: Int,
    val forksCount: Int
) {
    constructor(item: Item) : this(
        name = item.name,
        avatar = item.owner.avatarUrl,
        description = item.description.toString(),
        stargazersCount = item.stargazersCount,
        forksCount = item.forksCount,
        owner = item.owner.toString()
    )
}