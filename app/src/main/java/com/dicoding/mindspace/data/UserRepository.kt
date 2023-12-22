package com.dicoding.mindspace.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.mindspace.data.api.ApiService
import com.dicoding.mindspace.data.api.featureresponse.AnswerResponse
import com.dicoding.mindspace.data.api.featureresponse.QuestionsResponse
import com.dicoding.mindspace.data.api.model.RegisterModel
import com.dicoding.mindspace.data.api.model.UserLoginModel
import com.dicoding.mindspace.data.api.userresponse.LoginResponse
import com.dicoding.mindspace.data.api.userresponse.RegisterResponse
import com.dicoding.mindspace.data.api.userresponse.UserProfileResponse
import com.dicoding.mindspace.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val api: ApiService,
    private val apiNoHeader: ApiService,
    private val apiSurvey: ApiService,
    private val pref: UserPreference
) {

    fun register(
        registerModel: RegisterModel
    ) : LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiNoHeader.register(registerModel)
            if (response.status == 201) {
                emit(Result.Success(response))
            } else {
                emit(Result.Error(response.message!!))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun login(
        userLoginModel: UserLoginModel
    ) : LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiNoHeader.login(userLoginModel)
            if (response.status == 200) {
                emit(Result.Success(response))
            } else {
                emit(Result.Error(response.toString()))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getProfile() : LiveData<Result<UserProfileResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = api.getUserProfile()
            if (response.status == 200) {
                emit(Result.Success(response))
            } else {
                emit(Result.Error(response.toString()))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getUpdateToken(refreshToken: String) : LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiNoHeader.refreshToken(refreshToken)
            if (response.status == 200) {
                emit(Result.Success(response))
            } else {
                emit(Result.Error(response.toString()))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getQuestion() : LiveData<Result<QuestionsResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiSurvey.getQuestion()
            if (response.status == 200) {
                emit(Result.Success(response))
            } else {
                emit(Result.Error(response.toString()))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun submitAnswer() : LiveData<Result<AnswerResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiSurvey.submitAnswer()
            if (response.status == 200) {
                emit(Result.Success(response))
            } else {
                emit(Result.Error(response.toString()))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun saveSession(token: String) {
        pref.saveSession(token)
    }

    fun getSession(): Flow<String> {
        return pref.getSession()
    }

    suspend fun logout() {
        pref.logout()
    }




    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            apiNoHeader: ApiService,
            apiSurvey: ApiService,
            userPreference: UserPreference,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, apiNoHeader, apiSurvey , userPreference)
            }.also { instance = it }
    }
}