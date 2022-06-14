package com.example.gardeningservices.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cart (
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("idUser")
    @Expose
    var idUser: Int,
    @SerializedName("total")
    @Expose
    var total: Int,
    @SerializedName("state")
    @Expose
    var state: Int
)