package com.example.booksandroidapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.booksandroidapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

object Extensions {

    fun TextInputEditText.isNullOrEmpty(errorString: String): Boolean {
        val textInputLayout = this.parent.parent as TextInputLayout
        return if (text.toString().trim().isNotEmpty()) {
            textInputLayout.isErrorEnabled = false
            true
        } else {
            textInputLayout.error = errorString
            false
        }
    }

    fun ImageView.loadImageUrl(url: String) {
        val options = RequestOptions().centerCrop().placeholder(R.drawable.progress_animation)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()


        Glide.with(this).load(url).apply(options).into(this)
    }

}