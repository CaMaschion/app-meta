package com.app_meta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app_meta.ui.widget.CardRepositoryCustomView
import com.app_meta.R
import com.app_meta.network.model.Item

private const val FORKS = 1000
private const val FORKS_VIEW_TYPE = 2
private const val REPOSITORY_VIEW_TYPE = 1

class RepositoryAdapter(
    repositoryList: List<Item>,
    private val onClick: (Item) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ViewBase>()

    init {
        repositoryList.forEach {
            items.add(RepositoryViewType(it))

            if (it.forksCount > FORKS) {
                items.add(ForksViewType())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == REPOSITORY_VIEW_TYPE) {
            RepositoryViewHolder(CardRepositoryCustomView(parent.context))
        } else {
            ForksViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.forks_item, parent, false)
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoryViewHolder) {
            holder.bindItem((items[position] as RepositoryViewType).item, onClick)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].viewType

}

class ForksViewHolder(view: View) : RecyclerView.ViewHolder(view)

class RepositoryViewHolder(private val viewRepository: CardRepositoryCustomView) : RecyclerView.ViewHolder(viewRepository) {

    fun bindItem(item: Item, onClick: (Item) -> Unit) {
        viewRepository.setup(item)
        viewRepository.setOnClickListener { onClick(item)}
    }
}


abstract class ViewBase(val viewType: Int)
class ForksViewType : ViewBase(FORKS_VIEW_TYPE)
class RepositoryViewType(val item: Item) : ViewBase(REPOSITORY_VIEW_TYPE)