package com.dicoding.mindspace.view.emoticon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mindspace.data.api.ApiConfig
import com.dicoding.mindspace.data.api.featureresponse.EmoticonResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException


class EmoticonDetectionViewModel : ViewModel() {
    private val _emot = MutableLiveData<EmoticonResponse>()
    val emot : LiveData<EmoticonResponse> = _emot

    fun getEmoticon(text : String){
        viewModelScope.launch {
            try {
                val jsonString = "{\"text\":\"$text\"}"
                val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
                val requestBody = jsonString.toRequestBody(mediaType)
                val response = ApiConfig.getApiServiceEmot().getEmoticon(requestBody)
                _emot.value = response
            } catch (e : HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                _emot.value = Gson().fromJson(jsonInString, EmoticonResponse::class.java)
            }
        }
    }
}