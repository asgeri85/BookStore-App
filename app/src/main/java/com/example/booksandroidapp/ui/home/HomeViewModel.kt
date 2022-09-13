package com.example.booksandroidapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksandroidapp.model.BookResponse
import com.example.booksandroidapp.network.NetworkResult
import com.example.booksandroidapp.repository.HomeRepository
import com.example.booksandroidapp.utils.Constants.LIMIT
import com.example.booksandroidapp.utils.Constants.USER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) :ViewModel() {

    private val _bestBookData=MutableLiveData<BookResponse?>()
    val bestBookData:LiveData<BookResponse?>
        get() = _bestBookData

    private val _loading=MutableLiveData<Boolean>()
    val loading:LiveData<Boolean>
        get() = _loading

    private val _error=MutableLiveData<String?>()
    val error:LiveData<String?>
        get() = _error

    init {
        getBestBookData()
    }

    private fun getBestBookData(){
        _loading.value=true
        viewModelScope.launch {
            when(val request=homeRepository.getAllBook(USER, LIMIT)){
                is NetworkResult.Success->{
                    _bestBookData.value=request.data
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