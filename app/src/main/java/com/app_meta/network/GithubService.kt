package com.app_meta.network

import com.app_meta.network.model.GithubRepositories
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {

    @GET("/search/repositories?q=language:kotlin&sort=stars")
    fun getRepositories() : Call<GithubRepositories>
}