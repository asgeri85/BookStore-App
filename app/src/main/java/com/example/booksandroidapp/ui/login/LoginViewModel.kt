package com.example.booksandroidapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksandroidapp.model.AuthResultModel
import com.example.booksandroidapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _resultAuth = MutableLiveData<AuthResultModel?>()
    val resultAuth: LiveData<AuthResultModel?>
        get() = _resultAuth

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun signUp(email: String, password: String) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val result = authRepository.createAccount(email, password)
            _resultAuth.value = result
            _loading.value = false
        }

    }

    fun signIn(email: String, password: String) {
        _loading.value = true
        viewModelScope.launch {
            val result = authRepository.loginAccount(email, password)
            _resultAuth.value = result
            _loading.value = false
        }
    }
}


