package com.example.gardeningappmanager.network.user

import com.example.gardeningappmanager.model.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("get_user.php")
    suspend fun getUser(): List<User>
}