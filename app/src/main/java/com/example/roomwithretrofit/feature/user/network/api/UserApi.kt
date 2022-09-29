package com.example.roomwithretrofit.feature.user.network.api

import com.example.roomwithretrofit.feature.user.data.model.Response.UserBaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): Response<UserBaseResponse>
}