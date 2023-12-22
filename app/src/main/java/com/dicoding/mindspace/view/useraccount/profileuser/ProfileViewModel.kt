package com.dicoding.mindspace.view.useraccount.profileuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.mindspace.data.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository): ViewModel() {

    fun getSession() : LiveData<String> {
        return repository.getSession().asLiveData()
    }

    fun getProfile() = repository.getProfile()

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}