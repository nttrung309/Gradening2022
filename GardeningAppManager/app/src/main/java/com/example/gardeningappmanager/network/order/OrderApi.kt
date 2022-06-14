package com.example.gardeningappmanager.network.order

import com.example.gardeningappmanager.model.Order
import com.example.gardeningappmanager.model.OrderDetail
import com.example.gardeningappmanager.model.Product
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OrderApi {
    @POST("post_order.php")
    @FormUrlEncoded
    suspend fun postOrder(
        @Field("idUser") idUser: Int,
        @Field("idAddress") idAddress: Int,
        @Field("date") date: String,
        @Field("state") state: String,
        @Field("provisional_money") provisional_money: Int,
        @Field("shipping") shipping: Int,
        @Field("total") total: Int): Int

    @POST("post_order_detail.php")
    @FormUrlEncoded
    suspend fun postOrderDetail(
        @Field("idOrder") idOrder: Int,
        @Field("idProduct") idProduct: Int,
        @Field("quantity") quantity: Int): Boolean

    @POST("get_order.php")
    @FormUrlEncoded
    suspend fun getOrder(
        @Field("idUser") idUser: Int): List<Order>

    @POST("get_items_order.php")
    @FormUrlEncoded
    suspend fun getItemsOrder(
        @Field("idOrder") idOrder: String): List<Int>

    @POST("get_order_by_id.php")
    @FormUrlEncoded
    suspend fun getOrderById(
        @Field("idOrder") idOrder: Int): Order

    @POST("get_product_by_order.php")
    @FormUrlEncoded
    suspend fun getProductByOrder(
        @Field("idProduct") idProduct: String): List<Product>

    @POST("get_id_product_by_order.php")
    @FormUrlEncoded
    suspend fun getIdProductByOrder(
        @Field("idOrder") idOrder: Int): List<OrderDetail>


    @POST("get_order_by_state.php")
    @FormUrlEncoded
    suspend fun getOrderByState(
        @Field("state") state: String): List<Order>
    @POST("order_post_change_state.php")
    @FormUrlEncoded
    suspend fun postChangeOrderState(
        @Field("idOrder") idOrder: Int,
        @Field("state") state: String)
}
