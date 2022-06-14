package com.example.gardeningappmanager.model

import com.google.gson.annotations.SerializedName

class CRUDresponse (
    @SerializedName("success")
    val success: Int,
    @SerializedName("message")
    val message:String
)