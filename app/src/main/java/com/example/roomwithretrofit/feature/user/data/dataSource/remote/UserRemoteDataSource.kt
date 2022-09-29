package com.example.roomwithretrofit.feature.user.data.dataSource.remote

import com.example.roomwithretrofit.feature.user.data.model.Response.UserBaseResponse

interface UserRemoteDataSource {
    suspend fun getUsers(): UserBaseResponse?
}