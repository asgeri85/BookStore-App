package com.example.booksandroidapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorites_table")
@Parcelize
data class BookResponseItem(
    @SerializedName("category")
    val category: String?,
    @SerializedName("count")
    var count: String?,
    @SerializedName("description")
    val description: String?,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("image_three")
    val imageThree: String?,
    @SerializedName("image_two")
    val imageTwo: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("rate")
    val rate: String?,
    @SerializedName("sale_state")
    val saleState: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("user")
    val user: String?
):Parcelable