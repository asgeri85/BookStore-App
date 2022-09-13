package com.example.booksandroidapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BasketResponseModel(
    val price: String, val count: String,
):Parcelable