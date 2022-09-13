package com.example.booksandroidapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.booksandroidapp.model.BookResponseItem

@Database(entities = [BookResponseItem::class], version = 1, exportSchema = false)
abstract class BookStoreRoomDB :RoomDatabase(){
    abstract fun favoritesDAO():BookFavoritesDAO
}