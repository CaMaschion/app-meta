package com.app_meta

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.app_meta.model.Item
import com.bumptech.glide.Glide

class CardCustomView(context: Context) : LinearLayout(context) {

    private val avatar by lazy { findViewById<ImageView>(R.id.img_github) }
    private val repository by lazy { findViewById<TextView>(R.id.rep_github) }
    private val description by lazy { findViewById<TextView>(R.id.description_github)  }
    private val author by lazy { findViewById<TextView>(R.id.author_github) }
    private val forks by lazy { findViewById<TextView>(R.id.forks_github) }
    private val stars by lazy { findViewById<TextView>(R.id.stars_github) }

    init {
        orientation = VERTICAL
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        inflate(context, R.layout.list_items, this)
    }

    fun setup(item: Item) {

        Glide.with(context).load(item.owner.avatar_url).circleCrop().into(avatar)
        repository.text = item.name
        description.text = item.description
        author.text = item.owner.login
        forks.text = item.forks_count.toString()
        stars.text = item.stargazers_count.toString()
    }
}