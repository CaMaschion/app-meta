package com.app_meta.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app_meta.network.ApiService
import com.app_meta.network.GithubService
import com.app_meta.network.cache.CacheLocal
import com.app_meta.network.model.GithubRepositories
import com.app_meta.network.model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val REPOSITORY_KEY = "repository_key"

class RepositoryViewModel : ViewModel() {

    private val _repositories = MutableLiveData<List<Item>>()
    val repositories: LiveData<List<Item>>
        get() = _repositories

    fun fetchRepositories() {

        val request = ApiService.buildService(GithubService::class.java)
        val call = request.getRepositories()

        if(CacheLocal.repository.containsKey(REPOSITORY_KEY)){
            _repositories.postValue(CacheLocal.repository[REPOSITORY_KEY]?.items)
        } else {
            call.enqueue(object : Callback<GithubRepositories> {

                override fun onResponse(
                    call: Call<GithubRepositories>,
                    response: Response<GithubRepositories>
                ) {
                    if (response.isSuccessful) {
                        CacheLocal.repository[REPOSITORY_KEY] = response.body()!!

                        response.body()?.items.let {
                            _repositories.postValue(it)
                            Log.d(">>>>>>", "Hello")
                        }
                    }
                }

                override fun onFailure(call: Call<GithubRepositories>, t: Throwable) {
                    Log.d("ERROR", "Error")
                }
            })
        }
    }

//    fun fetchRepositories2(){
//
//        val request = ApiService.buildService(GithubService::class.java)
//
//        request.getRepositories().makeAsyncOperation(
//            REPOSITORY_KEY,
//            CacheLocal.repository,
//            onSuccess = { _repositories.postValue(it.items) }
//        )
//    }
}
