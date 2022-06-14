package com.example.gardeningservices.network.products

import com.example.gardeningservices.model.CRUDresponse
import com.example.gardeningservices.model.ProductDetail
import com.example.gardeningservices.model.Products
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApi {
    @FormUrlEncoded
    @POST("getProduct.php")
    fun getProduct(
        @Field("id") id: String): Call<List<Products>>

    @GET("getProductSaleHome.php")
    fun getSaleHome(): Call<List<Products>>

    @POST("getProductById.php")
    @FormUrlEncoded
    suspend fun getProductById(
        @Field("idProduct") idProduct: Int): Products

    @POST("get_product_list_by_cart_detail.php")
    @FormUrlEncoded
    suspend fun getProductListByCartDetail(
        @Field("idProduct") idProduct: String): List<Products>

    @POST("post_update_quantity_product.php")
    @FormUrlEncoded
    fun postUpdateQuantityProduct(
        @Field("quantity") quantity: Int,
        @Field("idProduct") idProduct: Int): Call<CRUDresponse>

    @POST("get_product_detail.php")
    @FormUrlEncoded
    suspend fun getProductDetail(
        @Field("idProduct") idProduct: Int): ProductDetail
}
