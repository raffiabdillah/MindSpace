package com.dicoding.mindspace.data.api.featureresponse

import com.google.gson.annotations.SerializedName

data class EmoticonResponse (
    @field:SerializedName("emotion")
    val emotion: String? = null,

    @field:SerializedName("error")
    val error: String? = null
)