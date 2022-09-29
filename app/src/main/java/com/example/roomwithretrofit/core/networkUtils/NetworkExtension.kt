package com.example.roomwithretrofit.core.networkUtils

import retrofit2.Response

fun <T> Response<T>.bodyOrThrow(): T? {
    if (isSuccessful) {
        return body()
    } else {
        throw ServerException(serverMessage = errorBody().toString(), code = code())
    }
}