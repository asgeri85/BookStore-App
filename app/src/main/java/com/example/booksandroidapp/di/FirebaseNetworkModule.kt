package com.example.booksandroidapp.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseNetworkModule {
    @Provides
    @Singleton
    fun provideFirebaseUser() = FirebaseAuth.getInstance()

}