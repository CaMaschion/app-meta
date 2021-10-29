package com.app_meta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app_meta.adapter.RepositoryAdapter
import com.app_meta.data.ApiService
import com.app_meta.data.GithubService
import com.app_meta.model.GithubRepositories
import com.app_meta.model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var githubAdapter: RepositoryAdapter
    lateinit var repos : MutableList<Item>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = layoutManager
        repos =  arrayListOf()

        getRepos()
    }

    private fun getRepos() {

        val request = ApiService.buildService(GithubService::class.java)
        val call = request.getRepositories()

        call.enqueue(object : Callback<GithubRepositories> {

            override fun onResponse(call: Call<GithubRepositories>, response: Response<GithubRepositories>) {
                if (response.isSuccessful) {

                    repos.addAll(response.body()!!.items)
                    githubAdapter = RepositoryAdapter(repos)
                    recyclerView.apply {
                        setHasFixedSize(true)
                        adapter = githubAdapter
                    }
                }
            }

            override fun onFailure(call: Call<GithubRepositories>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()

            }
        })
    }
}
