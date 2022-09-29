package com.example.roomwithretrofit.feature.user.data.repository.impl

import com.example.roomwithretrofit.core.networkUtils.ResultWrapper
import com.example.roomwithretrofit.core.networkUtils.safeApiCall
import com.example.roomwithretrofit.feature.user.data.dataSource.local.UserLocalDataSource
import com.example.roomwithretrofit.feature.user.data.dataSource.remote.UserRemoteDataSource
import com.example.roomwithretrofit.feature.user.data.model.entity.UserEntity
import com.example.roomwithretrofit.feature.user.data.model.entity.toUserEntity
import com.example.roomwithretrofit.feature.user.data.repository.UserRepository
import com.example.roomwithretrofit.feature.user.ui.model.UserView
import com.example.roomwithretrofit.feature.user.ui.model.toUserView
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    private suspend fun getUserEntityFromRemoteDatabase(): List<UserEntity>? =
        userRemoteDataSource.getUsers()?.data?.map {
            it.toUserEntity()
        }


    private suspend fun insertUserToLocalDatabase(users: List<UserEntity>) =
        userLocalDataSource.insertUsers(users)


    override suspend fun getUserViewFromLocalDataBase(): List<UserView> =
        userLocalDataSource.getUsers().map {
            it.toUserView()
        }


    override suspend fun getUsers(): ResultWrapper<List<UserView>> {
        return safeApiCall(
            localDataForErrorState = getUserViewFromLocalDataBase(),
            api = {
                val remoteData = getUserEntityFromRemoteDatabase()
                insertUserToLocalDatabase(remoteData!!)
                return@safeApiCall getUserViewFromLocalDataBase()
            }
        )
    }
}

