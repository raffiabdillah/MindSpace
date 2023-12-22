package com.dicoding.mindspace.view.register

import androidx.lifecycle.ViewModel
import com.dicoding.mindspace.data.UserRepository
import com.dicoding.mindspace.data.api.model.RegisterModel

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    fun register(registerModel: RegisterModel) = repository.register(registerModel)
}