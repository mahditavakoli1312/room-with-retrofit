package com.example.roomwithretrofit.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomwithretrofit.feature.user.data.db.dao.UserDao
import com.example.roomwithretrofit.feature.user.data.model.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}