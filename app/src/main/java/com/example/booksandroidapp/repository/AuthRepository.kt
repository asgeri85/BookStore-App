package com.example.booksandroidapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.booksandroidapp.model.AuthResultModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val auth: FirebaseAuth) {
    private val authModel = MutableLiveData<AuthResultModel>()

    suspend fun createAccount(email: String, password: String): AuthResultModel? {
        return try {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                authModel.value = AuthResultModel("Registration was successful", true)
            }.await()
            authModel.value
        } catch (e: Exception) {
            authModel.value = AuthResultModel(e.message, false)
            authModel.value
        }

    }

    suspend fun loginAccount(email: String, password: String): AuthResultModel? {
        return try {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                authModel.value = AuthResultModel("Login successful", true)
            }.await()
            authModel.value
        } catch (e: Exception) {
            authModel.value = AuthResultModel(e.message, false)
            authModel.value
        }
    }

    fun exitAccount(): AuthResultModel? {
        return try {
            auth.signOut()
            authModel.value = AuthResultModel("Your account has been logged out", true)
            authModel.value
        } catch (e: Exception) {
            authModel.value = AuthResultModel(e.message, false)
            authModel.value
        }
    }

}