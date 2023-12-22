package com.dicoding.mindspace.data.api.featureresponse

import com.google.gson.annotations.SerializedName

data class StoriesResponse(

    @field:SerializedName("Response")
    val response: List<StoryItem?> = emptyList()
)

data class StoryItem(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("emotion")
    val emotion: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("text")
    val text: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)
