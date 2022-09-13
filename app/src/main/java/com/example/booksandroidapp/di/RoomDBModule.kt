package com.example.booksandroidapp.di

import android.content.Context
import androidx.room.Room
import com.example.booksandroidapp.database.BookFavoritesDAO
import com.example.booksandroidapp.database.BookStoreRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideFavoritesRoomDB(@ApplicationContext appContext: Context): BookStoreRoomDB =
        Room.databaseBuilder(appContext, BookStoreRoomDB::class.java, "favorites_database")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideProductFavoriteDAO(bookStoreRoomDB: BookStoreRoomDB):BookFavoritesDAO=
        bookStoreRoomDB.favoritesDAO()

}