package com.app_meta.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app_meta.R
import com.app_meta.adapter.RepositoryAdapter
import com.app_meta.database.GithubRepositoriesRoomDatabase
import com.app_meta.ui.recyclerview.ItemDecorator
import com.app_meta.ui.viewmodel.GithubRepositoryViewModel
import com.app_meta.ui.viewmodel.GithubRepositoryViewModelFactory

class RepositoryFragment : Fragment(R.layout.fragment_recycler_view) {

    private val viewModel by lazy { ViewModelProvider(this).get(GithubRepositoryViewModel::class.java) }
    private lateinit var recycler: RecyclerView
    private val GithubRepositoryViewModel: GithubRepositoryViewModel by viewModels {
        GithubRepositoryViewModelFactory(GithubRepositoriesRoomDatabase.getDatabase(requireContext()).githubRepositoriesDao())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recycler_view)
        recycler.layoutManager = LinearLayoutManager(context)
        setupViewModel()
        decoratorRecyclerView()
    }

    private fun setupViewModel() {
        viewModel.fetchRepositories4()
        viewModel.repositories.observe(this){

                recycler.adapter = RepositoryAdapter(it) { content ->
                    findNavController().navigate(
                        RepositoryFragmentDirections.repositoryFragmentToContentFragment(content))
                }
            }
    }

    private fun decoratorRecyclerView() {
        recycler.apply {
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
