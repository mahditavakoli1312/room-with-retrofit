package com.example.roomwithretrofit.feature.user.data.model.Response

import com.google.gson.annotations.SerializedName

data class UserSupportResponse(
    @SerializedName("url") val url: String? = null,
    @SerializedName("text") val text: String? = null
)
