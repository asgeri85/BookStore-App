package com.example.booksandroidapp.ui.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksandroidapp.model.BookResponseItem
import com.example.booksandroidapp.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) :
    ViewModel() {

    private val _favoriteData = MutableLiveData<List<BookResponseItem>?>()
    val favoriteData: LiveData<List<BookResponseItem>?>
        get() = _favoriteData

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            try {
                _favoriteData.value = favoriteRepository.getFavoritesLocal()
            } catch (e: Exception) {
                _error.value = e.message
            }

        }
    }

    fun deleteBookFavorites(id: String) {
        viewModelScope.launch {
            try {
                favoriteRepository.deleteBookFavorites(id)
                getFavorites()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteFavorites() {
        viewModelScope.launch {
            try {
                favoriteRepository.clearFavorites()
                getFavorites()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}