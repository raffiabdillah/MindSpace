package com.dicoding.mindspace.data.api.featureresponse

import com.google.gson.annotations.SerializedName

data class QuestionsResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataItem(

	@field:SerializedName("question_text")
	val questionText: String? = null,

	@field:SerializedName("questionId")
	val questionId: String? = null,

	@field:SerializedName("option_text")
	val optionText: String? = null,

	@field:SerializedName("option_id")
	val optionId: String? = null,
)
