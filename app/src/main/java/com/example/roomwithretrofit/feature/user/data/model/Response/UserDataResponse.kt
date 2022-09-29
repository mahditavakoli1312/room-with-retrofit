package com.example.roomwithretrofit.feature.user.data.model.Response

import com.google.gson.annotations.SerializedName

data class UserDataResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("first_name") val firstName: String? = null,
    @SerializedName("last_name") val lastName: String? = null,
    @SerializedName("avatar") val avatar: String? = null
)
