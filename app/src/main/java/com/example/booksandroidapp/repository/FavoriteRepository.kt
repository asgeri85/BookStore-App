package com.example.booksandroidapp.repository

import com.example.booksandroidapp.database.BookFavoritesDAO
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val bookFavoritesDAO: BookFavoritesDAO) {

    suspend fun getFavoritesLocal() =
        bookFavoritesDAO.getAllFavorites()

    suspend fun deleteBookFavorites(id:String)=
        bookFavoritesDAO.deleteBook(id)

    suspend fun clearFavorites()=
        bookFavoritesDAO.deleteAllFavorites()
}