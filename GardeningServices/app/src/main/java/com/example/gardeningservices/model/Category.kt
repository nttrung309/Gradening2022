package com.example.gardeningservices.model

import com.google.gson.annotations.SerializedName


class Category (
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("image")
        val image: String
)