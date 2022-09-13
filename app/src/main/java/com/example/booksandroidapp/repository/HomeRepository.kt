package com.example.booksandroidapp.repository

import com.example.booksandroidapp.base.BaseRepository
import com.example.booksandroidapp.network.BooksApiDao
import javax.inject.Inject

class HomeRepository @Inject constructor(private val booksApiDao: BooksApiDao) : BaseRepository(){

    suspend fun getAllBook(user:String,limit:String)=safeApiRequest {
        booksApiDao.getAllBooksApi(user, limit)
    }
}