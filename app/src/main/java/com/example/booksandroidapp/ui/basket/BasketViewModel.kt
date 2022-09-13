package com.example.booksandroidapp.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksandroidapp.model.BookResponse
import com.example.booksandroidapp.model.CRUDResponse
import com.example.booksandroidapp.network.NetworkResult
import com.example.booksandroidapp.repository.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val basketRepository: BasketRepository) :
    ViewModel() {

    private val _basketData = MutableLiveData<BookResponse?>()
    val basketData: LiveData<BookResponse?>
        get() = _basketData

    private val _crudBasket = MutableLiveData<CRUDResponse?>()
    val crudBasket: LiveData<CRUDResponse?>
        get() = _crudBasket

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _totalBasket = MutableLiveData(0.0)
    val totalBasket: LiveData<Double>
        get() = _totalBasket

    init {
        getBasket()
    }

    fun getBasket() {
        _loading.value = true
        viewModelScope.launch {
            when (val request = basketRepository.getBasketData()) {
                is NetworkResult.Success -> {
                    _basketData.value = request.data
                    calculateAmount(request.data)
                    _loading.value = false
                }

                is NetworkResult.Error -> {
                    _error.value = request.message
                    _loading.value = false
                }
            }
        }
    }

    fun clearBasket() {
        _loading.value = true
        viewModelScope.launch {
            when (val request = basketRepository.clearBasketItems()) {
                is NetworkResult.Success -> {
                    _crudBasket.value = request.data
                }

                is NetworkResult.Error -> {
                    _error.value = request.message
                    _loading.value = false
                }
            }
        }
    }

    private fun calculateAmount(bookList: BookResponse?) {
        bookList?.let {
            it.forEach { it1 ->
                increase(it1.price!!.toDouble())
            }
        }
    }

    fun decrease(price: Double) {
        _totalBasket.value = _totalBasket.value?.minus(price)

    }

    fun increase(price: Double) {
        _totalBasket.value = _totalBasket.value?.plus(price)
    }
}