package com.example.gardeningservices.network.user

import com.example.gardeningservices.model.CRUDresponse
import com.example.gardeningservices.model.Users
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

interface UserApi {
    @FormUrlEncoded
    @POST("post_login.php")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String): Call<CRUDresponse>

    @FormUrlEncoded
    @POST("post_login.php")
    suspend fun dologin(
        @Field("username") username: String,
        @Field("password") password: String): List<Users>

    @FormUrlEncoded
    @POST("post_register.php")
    fun signUp(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String): Call<CRUDresponse>

    @FormUrlEncoded
    @POST("getUserById.php")
    suspend fun getUserById(
        @Field("idUser") idUser: Int) : Users

    @FormUrlEncoded
    @POST("post_update_edit_profile.php")
    suspend fun updateProfile(
        @Field("idUser") idUser: String,
        @Field("name") name: String,
        @Field("date") date: String,
        @Field("gender") gender: String,
        @Field("telephone") phoneNumber : String,
        @Field("email") email: String):     CRUDresponse

}