package com.example.examenintercam.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("setPicasso")
fun downloadImage(image: ImageView, url: String) {
    Picasso.get().load(url).into(image)
}