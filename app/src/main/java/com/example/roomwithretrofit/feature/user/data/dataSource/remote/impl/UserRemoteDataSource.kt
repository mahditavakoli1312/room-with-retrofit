package com.example.roomwithretrofit.feature.user.data.dataSource.remote.impl

import com.example.roomwithretrofit.core.networkUtils.bodyOrThrow
import com.example.roomwithretrofit.feature.user.data.dataSource.remote.UserRemoteDataSource
import com.example.roomwithretrofit.feature.user.data.model.Response.UserBaseResponse
import com.example.roomwithretrofit.feature.user.network.api.UserApi
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : UserRemoteDataSource {
    override suspend fun getUsers(): UserBaseResponse? {
        return userApi.getUsers().bodyOrThrow()
    }
}