package com.example.gardeningservices.network.order

import com.example.gardeningservices.network.ApiUtils

class OrderResponse {
    private  val orderApi = ApiUtils.createOrderApi()

    suspend fun postOrder(idUser: Int,
                          idAddress: Int,
                          date: String,
                          state: String,
                          provisional_money: Int,
                          shipping: Int,
                          total: Int) = orderApi.postOrder(idUser, idAddress, date, state, provisional_money, shipping, total)

    suspend fun postOrderDetail(idOrder: Int,
                                idProduct: Int,
                                quantity: Int) = orderApi.postOrderDetail(idOrder, idProduct, quantity)

    suspend fun getOrder(idUser: Int) = orderApi.getOrder(idUser)

    suspend fun getItemsOrder(idOrder: List<String>) = orderApi.getItemsOrder(idOrder.joinToString())

    suspend fun getOrderById(idOrder: Int) = orderApi.getOrderById(idOrder)

    suspend fun getProductByOrder(idProduct: String) = orderApi.getProductByOrder(idProduct)

    suspend fun getIdProductByOrder(idOrder: Int) = orderApi.getIdProductByOrder(idOrder)

}