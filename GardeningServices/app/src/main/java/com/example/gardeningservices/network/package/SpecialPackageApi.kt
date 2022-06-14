package com.example.gardeningservices.network.`package`

import com.example.gardeningservices.model.SpecialPackage
import retrofit2.Call
import retrofit2.http.GET

interface SpecialPackageApi {
    @GET("getSpecialPackage.php")
    fun getAllSpecialPackage(): Call<List<SpecialPackage>>

    @GET("getSpecialPackageHome.php")
    fun getSpecialPackageHome(): Call<List<SpecialPackage>>
}