package com.example.booksandroidapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksandroidapp.model.AuthResultModel
import com.example.booksandroidapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authRepository: AuthRepository) :ViewModel() {

    private val _resultAuth = MutableLiveData<AuthResultModel?>()
    val resultAuth: LiveData<AuthResultModel?>
        get() = _resultAuth

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun signOut(){
        _loading.value=true
        _resultAuth.value=authRepository.exitAccount()
        _loading.value=false
    }

}