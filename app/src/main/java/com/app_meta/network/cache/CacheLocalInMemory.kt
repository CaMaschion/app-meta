package com.app_meta.network.cache

import com.app_meta.network.model.GithubRepositories

object CacheLocal {

val repository = Cache<GithubRepositories>()

}

class Cache<T>: HashMap<String, T>()