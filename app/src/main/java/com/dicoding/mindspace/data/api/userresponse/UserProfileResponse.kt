package com.dicoding.mindspace.data.api.userresponse

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("user")
    val user: User? = null,
)

data class User(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,
)
