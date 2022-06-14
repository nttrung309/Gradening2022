package com.example.gardeningappmanager.network.product

import com.example.gardeningappmanager.model.Product
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApi {

    @GET("get_product.php")
    suspend fun getProduct(): List<Product>

    @POST("getProductById.php")
    @FormUrlEncoded
    suspend fun getProductById(
            @Field("idProduct") idProduct: Int): Product

    @POST("product_post_update.php")
    @FormUrlEncoded
    suspend fun postUpdateProduct(
            @Field("quantity") quantity: Int,
            @Field("price") price: Int,
            @Field("name") name: String,
            @Field("idProduct") idProduct: Int): Boolean
}