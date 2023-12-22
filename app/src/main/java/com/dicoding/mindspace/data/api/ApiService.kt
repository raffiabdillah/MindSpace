package com.dicoding.mindspace.data.api

import com.dicoding.mindspace.data.api.featureresponse.AnswerResponse
import com.dicoding.mindspace.data.api.featureresponse.EmoticonResponse
import com.dicoding.mindspace.data.api.featureresponse.QuestionsResponse
import com.dicoding.mindspace.data.api.featureresponse.StoryItem
import com.dicoding.mindspace.data.api.model.RegisterModel
import com.dicoding.mindspace.data.api.model.UserLoginModel
import com.dicoding.mindspace.data.api.userresponse.LoginResponse
import com.dicoding.mindspace.data.api.userresponse.RegisterResponse
import com.dicoding.mindspace.data.api.userresponse.UserProfileResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("signup")
    suspend fun register(
        @Body registerModel: RegisterModel
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body userLoginModel: UserLoginModel
    ): LoginResponse

    @GET("profile")
    suspend fun getUserProfile(): UserProfileResponse

    @GET("refresh")
    suspend fun refreshToken(
        @Header("Authorization") refreshToken: String
    ): LoginResponse

    @GET("question")
    suspend fun getQuestion(): QuestionsResponse

    @POST("answer")
    suspend fun submitAnswer() : AnswerResponse

    @POST("classify")
    suspend fun getEmoticon(
        @Body text: RequestBody
    ): EmoticonResponse

    @GET("entries")
    suspend fun getStories(
    ) : List<StoryItem>
}