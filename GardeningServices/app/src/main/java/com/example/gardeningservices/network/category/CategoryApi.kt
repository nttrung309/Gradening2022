package com.example.gardeningservices.network.category

import com.example.gardeningservices.model.Category
import retrofit2.Call
import retrofit2.http.GET

public interface CategoryApi {
    @GET("getCategory.php")
    fun getAllCategory(): Call<List<Category>>

    @GET("getCategoryHome.php")
    fun getCategoryHome(): Call<List<Category>>
}