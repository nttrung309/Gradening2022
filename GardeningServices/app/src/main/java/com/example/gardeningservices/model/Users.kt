package com.example.gardeningservices.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Users (
    @SerializedName("id")
    var id: Int,
    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("telephone")
    var telephone: String,
    @SerializedName("email")
    var email: String
)