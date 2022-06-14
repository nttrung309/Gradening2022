package com.example.gardeningservices.model

import com.google.gson.annotations.SerializedName

class ProductDetail (
    @SerializedName("idProduct")
    var idCategory: Int,
    @SerializedName("water")
    var water: String,
    @SerializedName("sunlight")
    var sunlight: String
)