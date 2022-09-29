package com.example.roomwithretrofit.feature.user.data.dataSource.local

import com.example.roomwithretrofit.feature.user.data.model.entity.UserEntity

interface UserLocalDataSource {
    suspend fun getUsers(): List<UserEntity>
    suspend fun insertUsers(users: List<UserEntity>)
}