package com.dicoding.mindspace.data.api.featureresponse

import com.google.gson.annotations.SerializedName

data class AnswerResponse (
    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("message")
    val message: Int? = null,

    )