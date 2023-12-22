package com.dicoding.mindspace.view.sharingsession

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mindspace.data.UserRepository
import com.dicoding.mindspace.data.api.ApiConfig
import com.dicoding.mindspace.data.api.featureresponse.StoryItem
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SharingSessionViewModel() : ViewModel() {
    private val _emot = MutableLiveData<List<StoryItem>>()
    val emot : LiveData<List<StoryItem>> = _emot

    fun getStory(){
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiServiceEmot().getStories()
                _emot.value = response
            } catch (_: HttpException){
            }
        }
    }
}