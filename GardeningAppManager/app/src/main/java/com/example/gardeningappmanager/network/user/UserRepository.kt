package com.example.gardeningappmanager.network.user

import com.example.gardeningappmanager.network.ApiUtils

class UserRepository {

    private val userApi = ApiUtils.createUserApi()

    suspend fun getUser() = userApi.getUser()

}