package com.example.gardeningservices.model

import com.google.gson.annotations.SerializedName

class OrderDetail (
    @SerializedName("id")
    val id: Int,
    @SerializedName("idOrder")
    val idOrder:Int,
    @SerializedName("idProduct")
    val idProduct:Int,
    @SerializedName("quantity")
    val quantity:String
)