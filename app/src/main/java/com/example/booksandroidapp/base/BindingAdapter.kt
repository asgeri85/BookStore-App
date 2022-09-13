package com.example.booksandroidapp.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.booksandroidapp.utils.Extensions.loadImageUrl

object BindingAdapter {

    @BindingAdapter("load_image")
    @JvmStatic
    fun loadImage(imageView: ImageView,imgUrl:String?){
        imgUrl?.let {
            imageView.loadImageUrl(it)
        }
    }
}