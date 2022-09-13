package com.example.booksandroidapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.booksandroidapp.model.BookResponseItem

@Dao
interface BookFavoritesDAO {

    @Insert
    suspend fun addBookFavorite(bookResponseItem: BookResponseItem)

    @Query("select * from favorites_table")
    suspend fun getAllFavorites():List<BookResponseItem>?

    @Query("Delete from favorites_table where id=:bookId ")
    suspend fun deleteBook(bookId:String)

    @Query("Delete from favorites_table")
    suspend fun deleteAllFavorites()
}