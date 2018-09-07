package com.asanarebel.githubapp.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.asanarebel.githubapp.R
import com.squareup.picasso.Picasso


@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Picasso
            .get()
            .load(url)
            .placeholder(R.color.lightGrey)
            .error(R.color.lightGrey)
            .into(this)
}