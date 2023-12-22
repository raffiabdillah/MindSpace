package com.dicoding.mindspace.view.getstarted

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mindspace.data.UserRepository

class WelcomeViewModel(private val repository: UserRepository) : ViewModel() {

    fun getSession() : LiveData<String> {
        return repository.getSession().asLiveData()
    }
}