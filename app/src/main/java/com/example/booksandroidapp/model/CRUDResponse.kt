package com.example.booksandroidapp.model


import com.google.gson.annotations.SerializedName

data class CRUDResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)