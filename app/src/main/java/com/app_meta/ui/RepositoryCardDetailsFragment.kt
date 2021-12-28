package com.app_meta.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.app_meta.R

class RepositoryCardDetailsFragment:Fragment (R.layout.fragment_card_details_repository) {

    private val args: RepositoryCardDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.author_github).text = args.details.owner.login
        view.findViewById<TextView>(R.id.description_github).text = args.details.description
        view.findViewById<TextView>(R.id.forks_github).text = args.details.forks_count.toString()
    }

}