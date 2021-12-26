package com.app_meta.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app_meta.R
import com.app_meta.adapter.RepositoryAdapter

class RepositoryFragment : Fragment(R.layout.fragment_repository) {

    private val viewModel by lazy { ViewModelProvider(this).get(RepositoryViewModel::class.java) }
    private val recyclerView: RecyclerView by lazy { requireView().findViewById(R.id.recycler_view) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.fetchRepositories()

        viewModel.repositories.observe(this){
            recyclerView.apply {
                recyclerView.adapter = RepositoryAdapter(it) { content ->
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