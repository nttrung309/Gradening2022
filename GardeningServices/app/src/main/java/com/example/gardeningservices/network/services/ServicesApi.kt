package com.example.gardeningservices.network.services

import com.example.gardeningservices.model.Category
import com.example.gardeningservices.model.Services
import retrofit2.Call
import retrofit2.http.GET

interface ServicesApi {
    @GET("getServices.php")
    fun getAllServices(): Call<List<Services>>

    @GET("getServicesHome.php")
    fun getServicesHome(): Call<List<Services>>

    @GET("getServiceDetail.php")
    fun getServiceDetail():Call<Services>

}