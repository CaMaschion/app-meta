package com.app_meta.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app_meta.R
import com.app_meta.adapter.RepositoryAdapter
import com.app_meta.ui.recyclerview.ItemDecorator
import com.app_meta.ui.viewmodel.RepositoryViewModel

class RepositoryFragment : Fragment(R.layout.fragment_repository) {

    private val viewModel by lazy { ViewModelProvider(this).get(RepositoryViewModel::class.java) }
    private lateinit var recycler: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recycler_view)
        recycler.layoutManager = LinearLayoutManager(context)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.fetchRepositories()
        viewModel.repositories.observe(this){
            recycler.apply {

                recycler.adapter = RepositoryAdapter(it) { content ->
                    findNavController().navigate(
                        RepositoryFragmentDirections.repositoryFragmentToContentFragment(content))
                }

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