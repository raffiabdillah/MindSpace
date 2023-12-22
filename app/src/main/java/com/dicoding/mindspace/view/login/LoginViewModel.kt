package com.dicoding.mindspace.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mindspace.data.UserRepository
import com.dicoding.mindspace.data.api.model.UserLoginModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    fun login(userLoginModel: UserLoginModel) = repository.login(userLoginModel)

    fun saveSession(token: String) {
        viewModelScope.launch {
            repository.saveSession(token)
        }
    }
}