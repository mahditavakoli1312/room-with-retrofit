package com.example.roomwithretrofit.feature.user.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomwithretrofit.feature.user.data.model.Response.UserDataResponse

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)

fun UserDataResponse.toUserEntity(): UserEntity =
    UserEntity(
        email = email ?: "",
        firstName = firstName ?: "",
        lastName = lastName ?: "",
        avatar = avatar ?: ""
    )
