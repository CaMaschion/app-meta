package com.app_meta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app_meta.adapter.RepositoryAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(RepositoryViewModel::class.java) }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recycler_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.fetchRepositories()

        viewModel.repositories.observe(this){
            recyclerView.apply {
                recyclerView.adapter = RepositoryAdapter(it)
                setHasFixedSize(true)
                addItemDecoration(
                    ItemDecorator(
                        resources.getDimensionPixelOffset(R.dimen.dimen_16),
                        resources.getDimensionPixelOffset(R.dimen.dimen_10)
                    )
                )
            }
        }
    }
}
