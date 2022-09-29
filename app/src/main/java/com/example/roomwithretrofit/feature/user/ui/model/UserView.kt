package com.example.roomwithretrofit.feature.user.ui.model

import com.example.roomwithretrofit.feature.user.data.model.entity.UserEntity

data class UserView(
    val id: Int? = 0,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?
)

fun UserEntity.toUserView(): UserView = UserView(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar
)