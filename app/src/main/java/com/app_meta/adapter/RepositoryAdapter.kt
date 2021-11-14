package com.app_meta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app_meta.R
import com.app_meta.model.Item
import com.bumptech.glide.Glide

class RepositoryAdapter(private val repositoryList: List<Item>) :
    RecyclerView.Adapter<RepositoryAdapter.GithubViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        if (viewType > 1000) {
            val items = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_items_purple_light, parent, false)

            return GithubViewHolder(items)
        }
        val items = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_items_purple_dark, parent, false)

        return GithubViewHolder(items)
    }

    override fun getItemViewType(position: Int): Int {
        return repositoryList.elementAt(position).forks_count
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        return holder.bindItem(repositoryList[position])
    }

    override fun getItemCount() = repositoryList.size

    class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val avatar: ImageView = itemView.findViewById(R.id.img_github)
        private val repository: TextView = itemView.findViewById(R.id.rep_github)
        private val description: TextView = itemView.findViewById(R.id.description_github)
        private val author: TextView = itemView.findViewById(R.id.author_github)
        private val forks: TextView = itemView.findViewById(R.id.forks_github)
        private val stars: TextView = itemView.findViewById(R.id.stars_github)

        fun bindItem(item: Item) {
            repository.text = item.name
            description.text = item.description
            author.text = item.owner.login
            forks.text = item.forks_count.toString()
            stars.text = item.stargazers_count.toString()
            Glide.with(itemView.context).load(item.owner.avatar_url).circleCrop().into(avatar)
        }
    }
}


