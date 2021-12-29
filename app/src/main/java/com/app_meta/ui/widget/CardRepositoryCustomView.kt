package com.app_meta.ui.widget

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.app_meta.R
import com.app_meta.network.model.Item
import com.bumptech.glide.Glide

class CardRepositoryCustomView(context: Context) : LinearLayout(context) {

    private val avatar by lazy { findViewById<ImageView>(R.id.img_github) }
    private val repository by lazy { findViewById<TextView>(R.id.rep_github) }
    private val stars by lazy { findViewById<TextView>(R.id.stars_github) }

    init {
        orientation = VERTICAL
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        inflate(context, R.layout.fragment_card_repository, this)
    }

    fun setup(item: Item) {

        Glide.with(context).load(item.owner.avatarUrl).circleCrop().into(avatar)
        repository.text = item.name
        stars.text = item.stargazersCount.toString()
    }

}
