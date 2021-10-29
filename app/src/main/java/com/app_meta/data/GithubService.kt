package com.app_meta.data

import com.app_meta.model.GithubRepositories
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {

    @GET("/search/repositories?q=language:kotlin&sort=stars")
    fun getRepositories() : Call<GithubRepositories>
}