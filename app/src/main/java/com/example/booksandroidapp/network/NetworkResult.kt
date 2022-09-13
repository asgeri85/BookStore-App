package com.example.booksandroidapp.network


sealed class NetworkResult<T>(
    val data:T?=null,
    val message:String?=null,
    val networkError:Boolean=false
){
    class Success <T>(data: T?):NetworkResult<T>(data)
    class Error<T>(networkError: Boolean,message: String?):NetworkResult<T>(null,message=message,networkError=networkError)
}
