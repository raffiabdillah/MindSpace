package com.dicoding.mindspace.di

import android.content.Context
import com.dicoding.mindspace.data.UserRepository
import com.dicoding.mindspace.data.api.ApiConfig
import com.dicoding.mindspace.data.pref.UserPreference
import com.dicoding.mindspace.data.pref.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val userToken = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(userToken)
        val apiServiceWithoutHeader = ApiConfig.getApiServiceNoHeader()
        val apiServiceSurvey = ApiConfig.getApiSurvey(userToken)
        return UserRepository.getInstance(apiService, apiServiceWithoutHeader, apiServiceSurvey, pref)
    }
}