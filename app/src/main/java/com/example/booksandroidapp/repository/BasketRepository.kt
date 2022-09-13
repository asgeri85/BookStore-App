package com.example.booksandroidapp.repository

import com.example.booksandroidapp.base.BaseRepository
import com.example.booksandroidapp.network.BooksApiDao
import com.example.booksandroidapp.utils.Constants.USER
import javax.inject.Inject

class BasketRepository @Inject constructor(private val  booksApiDao: BooksApiDao) :BaseRepository() {

    suspend fun getBasketData()=safeApiRequest {
        booksApiDao.getBasketApi(USER)
    }

    suspend fun clearBasketItems()=safeApiRequest {
        booksApiDao.clearBasketApi(USER)
    }
}