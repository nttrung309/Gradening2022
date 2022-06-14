package com.example.gardeningservices.network.products

import com.example.gardeningservices.model.CRUDresponse
import com.example.gardeningservices.network.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {

    private  val productApi = ApiUtils.createProductApi()

    suspend fun getProductById(idProduct: Int) = productApi.getProductById(idProduct)

    suspend fun getProductDetail(idProduct: Int) = productApi.getProductDetail(idProduct)

    suspend fun getProductListByCartDetail(idProduct: List<String>) = productApi.getProductListByCartDetail(idProduct.joinToString())

    fun postUpdateQuantityProduct(quantity:Int,idProduct: Int) {
        productApi.postUpdateQuantityProduct(quantity,idProduct).enqueue(object : Callback<CRUDresponse>{
            override fun onFailure(call: Call<CRUDresponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<CRUDresponse>, response: Response<CRUDresponse>) {

            }

        })
    }

}