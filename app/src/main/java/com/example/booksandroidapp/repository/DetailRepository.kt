package com.example.booksandroidapp.repository

import com.example.booksandroidapp.base.BaseRepository
import com.example.booksandroidapp.database.BookFavoritesDAO
import com.example.booksandroidapp.model.BookResponseItem
import com.example.booksandroidapp.network.BooksApiDao
import javax.inject.Inject

class DetailRepository @Inject constructor(private val booksApiDao: BooksApiDao,private val bookFavoritesDAO: BookFavoritesDAO) :
    BaseRepository() {

    suspend fun addBasket(bookResponseItem: BookResponseItem, count: String) = safeApiRequest {
        booksApiDao.addToBasketApi(bookResponseItem.user!!,
            bookResponseItem.title!!,
            bookResponseItem.price!!,
            bookResponseItem.description!!,
            bookResponseItem.category!!,
            bookResponseItem.image!!,
            bookResponseItem.rate!!,
            count,
            "",
            "",
            "")
    }

    suspend fun addFavoritesLocal(bookResponseItem: BookResponseItem)=
        bookFavoritesDAO.addBookFavorite(bookResponseItem)
}