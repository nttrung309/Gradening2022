package com.example.gardeningappmanager.network

import com.example.gardeningappmanager.network.address.AddressApi
import com.example.gardeningappmanager.network.order.OrderApi
import com.example.gardeningappmanager.network.product.ProductApi
import com.example.gardeningappmanager.network.user.UserApi

class ApiUtils {
    companion object {

        private val URL = "https://roadtouni.000webhostapp.com/"

        fun createUserApi(): UserApi {
            return Retrofit.getClient(URL).create(UserApi::class.java)
        }
        fun createProductApi(): ProductApi {
            return Retrofit.getClient(URL).create(ProductApi::class.java)
        }
        fun createOrderApi(): OrderApi {
            return Retrofit.getClient(URL).create(OrderApi::class.java)
        }
        fun createAddressApi(): AddressApi {
            return Retrofit.getClient(URL).create(AddressApi::class.java)
        }
    }
}