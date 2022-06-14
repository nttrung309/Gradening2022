package com.example.gardeningservices.model

import com.google.gson.annotations.SerializedName

class CartDetail (
    @SerializedName("id")
    var id: Int,
    @SerializedName("idCart")
    var idCart: Int,
    @SerializedName("idProduct")
    var idProduct: Int,
    @SerializedName("quantity")
    var quantity: Int
)