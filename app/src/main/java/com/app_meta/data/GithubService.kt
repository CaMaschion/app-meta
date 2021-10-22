package com.app_meta.data

import com.app_meta.model.Repositories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("/search/repositories?q=language:kotlin&sort=stars")
    fun getRepositories(@Query("page")page: Int ) : Call<Repositories>
}