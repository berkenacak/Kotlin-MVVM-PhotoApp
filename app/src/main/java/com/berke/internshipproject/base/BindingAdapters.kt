package com.berke.internshipproject.base

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.berke.internshipproject.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageUrl")
fun AppCompatImageView.setImageUrl(url: String?) {
    Glide
        .with(context.applicationContext)
        .load(url)
        .placeholder(R.drawable.pixabay)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

}