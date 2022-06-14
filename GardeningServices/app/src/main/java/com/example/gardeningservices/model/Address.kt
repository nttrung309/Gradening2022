package com.example.gardeningservices.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Address (
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("idUser")
    @Expose
    var idUser: String,
    @SerializedName("address_name")
    @Expose
    var address_name: String,
    @SerializedName("address_number")
    @Expose
    var address_number: String,
    @SerializedName("address_line")
    @Expose
    var address_line: String,
    @SerializedName("province")
    @Expose
    var province: String,
    @SerializedName("district")
    @Expose
    var district: String,
    @SerializedName("ward")
    @Expose
    var ward: String
)