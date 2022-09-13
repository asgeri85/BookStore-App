package com.example.booksandroidapp.base

import com.example.booksandroidapp.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> safeApiRequest(apiRequest:suspend () -> T):NetworkResult<T>{
        return withContext(Dispatchers.IO){
            try {
                NetworkResult.Success(apiRequest.invoke())
            }catch (throwable:Throwable){
                when(throwable){
                    is HttpException->{
                        NetworkResult.Error(false,throwable.response()?.errorBody()?.toString())
                    }
                    else->NetworkResult.Error(true,throwable.localizedMessage)
                }
            }
        }
    }
}