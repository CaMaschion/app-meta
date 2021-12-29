package com.app_meta.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app_meta.dao.GithubRepositoriesDao
import com.app_meta.extension.doRequest
import com.app_meta.network.ApiService
import com.app_meta.network.GithubService
import com.app_meta.network.model.GithubRepositories
import com.app_meta.network.model.Item
import com.app_meta.network.model.RepositoryEntity

const val REPOSITORY_KEY = "repository_key"

class GithubRepositoryViewModel(private val dao: GithubRepositoriesDao) : ViewModel() {

        private val request = ApiService.buildService(GithubService::class.java)
        val call = request.getRepositories()

    private val _repositories = MutableLiveData<List<Item>>()
    val repositories: LiveData<List<Item>>
        get() = _repositories

//    fun fetchRepositories() {
//
//        if(CacheLocal.repository.containsKey(REPOSITORY_KEY)){
//            _repositories.postValue(CacheLocal.repository[REPOSITORY_KEY]?.items)
//        } else {
//            call.enqueue(object : Callback<GithubRepositories> {
//
//                override fun onResponse(
//                    call: Call<GithubRepositories>,
//                    response: Response<GithubRepositories>
//                ) {
//                    if (response.isSuccessful) {
//                        CacheLocal.repository[REPOSITORY_KEY] = response.body()!!
//
//                        response.body()?.items.let {
//                            _repositories.postValue(it)
//                            Log.d(">>>>>>", "Hello")
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<GithubRepositories>, t: Throwable) {
//                    Log.d("ERROR", "Error")
//                }
//            })
//        }
//    }

//    fun fetchRepositories2(){
//
//        request.getRepositories().makeAsyncOperation(
//            REPOSITORY_KEY,
//            CacheLocal.repository,
//            onSuccess = { _repositories.postValue(it.items) }
//        )
//    }

//    fun fetchRepositories3() {
//
//        request.getRepositories().doRequest(
//            REPOSITORY_KEY,
//            GithubRepositories::class.java,
//            onSuccess = { _repositories.postValue(it.items) }
//        )
//    }

    fun fetchRepositories4() {

        val githubRepository = dao.getRepositoryEntity()

        if (githubRepository.isEmpty()) {
            request.getRepositories().doRequest(
                REPOSITORY_KEY,
                GithubRepositories::class.java,
                onSuccess = {
                    dao.insert(it.items.map(::RepositoryEntity))
                    _repositories.postValue(it.items)
                }
            )
        } else {
            _repositories.postValue(githubRepository.map(::Item))
        }
    }

}

class GithubRepositoryViewModelFactory(private val dao: GithubRepositoriesDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GithubRepositoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GithubRepositoryViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}