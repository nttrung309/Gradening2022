package com.example.gardeningservices.model

import com.google.gson.annotations.SerializedName

class Products(
    @SerializedName("id")
    var id: Int,
    @SerializedName("idCategory")
    var idCategory: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("discount")
    var discount: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("quantity")
    var quantity: Int,
    @SerializedName("rating")
    var rating: String,
    @SerializedName("note")
    var note: String

)