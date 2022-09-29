package com.example.roomwithretrofit.feature.user.di

import com.example.roomwithretrofit.core.db.AppDatabase
import com.example.roomwithretrofit.feature.user.data.dataSource.local.UserLocalDataSource
import com.example.roomwithretrofit.feature.user.data.dataSource.local.impl.UserLocalDataSourceImpl
import com.example.roomwithretrofit.feature.user.data.dataSource.remote.UserRemoteDataSource
import com.example.roomwithretrofit.feature.user.data.dataSource.remote.impl.UserRemoteDataSourceImpl
import com.example.roomwithretrofit.feature.user.data.repository.UserRepository
import com.example.roomwithretrofit.feature.user.data.repository.impl.UserRepositoryImpl
import com.example.roomwithretrofit.feature.user.network.api.UserApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModules {

    @Binds
    abstract fun bindUserRemoteDataSource(impl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    abstract fun bindUserLocalDataSource(impl: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    abstract fun bindUserRepositry(impl: UserRepositoryImpl): UserRepository

    companion object {

        @Provides
        fun provideUserDao(database: AppDatabase) =
            database.getUserDao()

        @Provides
        fun provideUserApi(retrofit: Retrofit): UserApi =
            retrofit.create(UserApi::class.java)
    }
}