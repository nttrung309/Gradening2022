package com.example.gardeningservices.model

import com.google.gson.annotations.SerializedName

class Order (
    @SerializedName("id")
    val id: Int,
    @SerializedName("idUser")
    val idUser:Int,
    @SerializedName("idAddress")
    val idAddress:Int,
    @SerializedName("date")
    val date:String,
    @SerializedName("state")
    val state:String,
    @SerializedName("provisional_money")
    val provisional_money:Int,
    @SerializedName("shipping")
    val shipping:Int,
    @SerializedName("total")
    val total:Int
)