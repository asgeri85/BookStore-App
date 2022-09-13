package com.example.booksandroidapp.network

import com.example.booksandroidapp.model.BookResponse
import com.example.booksandroidapp.model.CRUDResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BooksApiDao {

    @POST("get_products_by_user.php")
    @FormUrlEncoded
    suspend fun getAllBooksApi(
        @Field("user") user: String,
        @Field("limit") limit: String,
    ): BookResponse

    @POST("add_to_bag.php")
    @FormUrlEncoded
    suspend fun addToBasketApi(
        @Field("user") user: String, @Field("title") title: String, @Field("price") price: String,
        @Field("description") description: String, @Field("category") category: String,
        @Field("image") image: String, @Field("rate") rate: String,
        @Field("count") count: String,@Field("image_two") image_two: String,
        @Field("image_three") image_three: String,@Field("sale_state") sale_state: String
    ):CRUDResponse

    @POST("get_bag_products_by_user.php")
    @FormUrlEncoded
    suspend fun getBasketApi(@Field("user") user: String):BookResponse

    @POST("clear_bag.php")
    @FormUrlEncoded
    suspend fun clearBasketApi(@Field("user") user: String):CRUDResponse
}