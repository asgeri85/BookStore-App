package com.example.booksandroidapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksandroidapp.model.BookResponseItem
import com.example.booksandroidapp.model.CRUDResponse
import com.example.booksandroidapp.network.NetworkResult
import com.example.booksandroidapp.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepository: DetailRepository) :
    ViewModel() {

    private val _bagResponse = MutableLiveData<CRUDResponse?>()
    val bagResponse: LiveData<CRUDResponse?>
        get() = _bagResponse

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _favoriteResponse=MutableLiveData<Boolean?>()
    val favoriteResponse:LiveData<Boolean?>
        get() = _favoriteResponse

    fun addBasket(bookResponseItem: BookResponseItem, count: String) {
        _loading.value = true
        viewModelScope.launch {
            when (val request = detailRepository.addBasket(bookResponseItem, count)) {
                is NetworkResult.Success -> {
                    _bagResponse.value = request.data
                    _loading.value = false
                }

                is NetworkResult.Error -> {
                    _error.value = request.message
                    _loading.value = false
                }
            }
        }
    }

    fun addFavorites(bookResponseItem: BookResponseItem) {
        viewModelScope.launch {
            try {
                detailRepository.addFavoritesLocal(bookResponseItem)
                _favoriteResponse.value=true
            }catch (e:Exception){
                _error.value="Book available in favorites"
            }
        }
    }

}