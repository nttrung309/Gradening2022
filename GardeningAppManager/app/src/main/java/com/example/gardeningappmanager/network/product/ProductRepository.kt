package com.example.gardeningappmanager.network.product

import com.example.gardeningappmanager.network.ApiUtils
import retrofit2.http.Field

class ProductRepository {
    private val productApi = ApiUtils.createProductApi()

    suspend fun getProduct() = productApi.getProduct()

    suspend fun postUpdateProduct(
            quantity: Int,
            price: Int,
            name: String,

            idProduct: Int) = productApi.postUpdateProduct(quantity,price,name,idProduct)

}