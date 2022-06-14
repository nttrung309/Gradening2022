package com.example.gardeningservices.model

import com.google.gson.annotations.SerializedName

class SpecialPackage(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("discount")
    val discount:String,
    @SerializedName("image")
    val image: String
)