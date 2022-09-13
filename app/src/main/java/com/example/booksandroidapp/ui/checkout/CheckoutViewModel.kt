package com.example.booksandroidapp.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksandroidapp.model.CRUDResponse
import com.example.booksandroidapp.network.NetworkResult
import com.example.booksandroidapp.repository.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val basketRepository: BasketRepository) :ViewModel() {

    private val _crudCheckout=MutableLiveData<CRUDResponse?>()
    val crudCheckout:LiveData<CRUDResponse?>
        get() = _crudCheckout

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    fun completeOrder(){
        _loading.value=true
        viewModelScope.launch {
            when(val request=basketRepository.clearBasketItems()){
                is NetworkResult.Success->{
                    _crudCheckout.value=request.data
                    _loading.value=false
                }
                is NetworkResult.Error->{
                    _error.value=request.message
                    _loading.value=false
                }
            }
        }
    }


}