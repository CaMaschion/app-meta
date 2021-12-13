package com.app_meta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app_meta.data.ApiService
import com.app_meta.data.GithubService
import com.app_meta.model.GithubRepositories
import com.app_meta.model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel : ViewModel() {

    private val _repositories = MutableLiveData<List<Item>>()
    val repositories: LiveData<List<Item>>
        get() = _repositories

    fun fetchRepositories() {

        if (repositories.value?.isEmpty() == true) {
            request()
        }
    }

    private fun request() {
        val request = ApiService.buildService(GithubService::class.java)
        val call = request.getRepositories()

        call.enqueue(object : Callback<GithubRepositories> {

            override fun onResponse(
                call: Call<GithubRepositories>,
                response: Response<GithubRepositories>
            ) {
                if (response.isSuccessful) {
                    response.body()?.items.let {
                       _repositories.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<GithubRepositories>, t: Throwable) {
//                Toast.makeText(this, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
