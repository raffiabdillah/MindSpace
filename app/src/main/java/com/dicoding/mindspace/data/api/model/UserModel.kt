package com.dicoding.mindspace.data.api.model

data class RegisterModel(
    val name: String,
    val email: String,
    val password: String
)

data class UserLoginModel(
    val email: String,
    val password: String
)