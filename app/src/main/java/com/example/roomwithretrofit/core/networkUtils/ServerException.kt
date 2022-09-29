package com.example.roomwithretrofit.core.networkUtils

class ServerException constructor(val serverMessage: String, val code: Int) : Exception()