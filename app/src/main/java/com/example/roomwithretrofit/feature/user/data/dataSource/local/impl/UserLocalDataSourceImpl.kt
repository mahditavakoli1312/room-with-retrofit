package com.example.roomwithretrofit.feature.user.data.dataSource.local.impl

import com.example.roomwithretrofit.feature.user.data.dataSource.local.UserLocalDataSource
import com.example.roomwithretrofit.feature.user.data.db.dao.UserDao
import com.example.roomwithretrofit.feature.user.data.model.entity.UserEntity
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserLocalDataSource {

    override suspend fun getUsers(): List<UserEntity> =
        userDao.getUsers()


    override suspend fun insertUsers(users: List<UserEntity>) =
        userDao.insertUsers(users = users)
}