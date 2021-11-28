package com.app_meta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.app_meta.CardCustomView
import com.app_meta.R
import com.app_meta.model.Item

private const val FORKS = 10000
private const val FORKS_PURPLE = 1
private const val FORKS_PURPLE_LIGHT = 2

class RepositoryAdapter(
    repositoryList: List<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ViewBase>()

    init {
        repositoryList.forEach {
            items.add(PurpleViewType(it))

            if (it.forks_count > FORKS){
                items.add(PurpleLightViewType())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == FORKS_PURPLE) {
            PurpleViewHolder(CardCustomView(parent.context))
        } else {
            PurpleLightViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_items, parent, false))
        }

    override fun getItemViewType(position: Int) = items[position].viewType

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PurpleViewHolder) {
            holder.bindItem((items[position] as PurpleViewType).item)
        }
    }

    override fun getItemCount() = items.size

    class PurpleLightViewHolder(viewColorLight: View) : RecyclerView.ViewHolder(viewColorLight)

    class PurpleViewHolder(private val view: CardCustomView) : RecyclerView.ViewHolder(view) {

        fun bindItem(item: Item) {
            view.setup(item)
        }
    }
}

abstract class ViewBase(val viewType: Int)
class PurpleLightViewType: ViewBase(FORKS_PURPLE_LIGHT)
class PurpleViewType(val item: Item) : ViewBase(FORKS_PURPLE)