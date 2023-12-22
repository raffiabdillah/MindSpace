package com.dicoding.mindspace.data.api.userresponse

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("user_id")
    val user_id: String? = null
)
