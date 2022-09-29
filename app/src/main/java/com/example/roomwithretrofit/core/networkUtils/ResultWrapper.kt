package com.example.roomwithretrofit.core.networkUtils

sealed interface ResultWrapper<out T> {
    data class Success<T>(val data: T) : ResultWrapper<T>
    data class Failure<T>(val severMessage: String, val code: Int, val data: T) : ResultWrapper<T>
    data class AppError<T>(val appMessage: String, val data: T) : ResultWrapper<T>
}