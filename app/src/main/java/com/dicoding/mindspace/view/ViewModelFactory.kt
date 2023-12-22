package com.dicoding.mindspace.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mindspace.data.UserRepository
import com.dicoding.mindspace.di.Injection
import com.dicoding.mindspace.view.emoticon.EmoticonDetectionViewModel
import com.dicoding.mindspace.view.emoticon.stories.StoriesViewModel
import com.dicoding.mindspace.view.getstarted.WelcomeViewModel
import com.dicoding.mindspace.view.home.HomeViewModel
import com.dicoding.mindspace.view.login.LoginViewModel
import com.dicoding.mindspace.view.register.RegisterViewModel
import com.dicoding.mindspace.view.sharingsession.SharingSessionViewModel
import com.dicoding.mindspace.view.useraccount.profileuser.ProfileViewModel

class ViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java)-> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(WelcomeViewModel::class.java)-> {
                WelcomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java)-> {
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SharingSessionViewModel::class.java)-> {
                SharingSessionViewModel() as T
            }
            modelClass.isAssignableFrom(EmoticonDetectionViewModel::class.java) -> {
                EmoticonDetectionViewModel() as T
            }
            modelClass.isAssignableFrom(StoriesViewModel::class.java) -> {
                StoriesViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}