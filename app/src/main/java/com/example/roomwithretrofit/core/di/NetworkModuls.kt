package com.example.roomwithretrofit.core.di

import com.example.roomwithretrofit.core.data.URLs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModuls {

    @Provides
    @Named("base_url")
    fun provideBaseurl(): String = URLs.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("base_url") baseUrl: String
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}