package com.example.roomwithretrofit.feature.user.data.repository

import com.example.roomwithretrofit.core.networkUtils.ResultWrapper
import com.example.roomwithretrofit.feature.user.ui.model.UserView

interface UserRepository {
    suspend fun getUsers(): ResultWrapper<List<UserView>>
    suspend fun getUserViewFromLocalDataBase(): List<UserView>
}