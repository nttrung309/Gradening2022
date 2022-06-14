package com.example.gardeningservices.utilities

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.security.MessageDigest
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat

public class Converter(private val base64String: String) {
    public fun DecodeToImage(): Bitmap {
        val imageBytes = Base64.decode(base64String, android.util.Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
    public fun md5(): String {
        return hashString(base64String, "MD5")
    }

    public fun sha256(): String {
        return hashString(base64String, "SHA-256")
    }

    public fun hashString(input: String, algorithm: String): String {
        return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
    }
    companion object {
        public fun convertMoney(number: Int): String{
            val format: NumberFormat = DecimalFormat("###,###,###")
            val s = format.format(number)
            return "$s VNĐ"
        }
        @SuppressLint("SimpleDateFormat")
        public fun convertDate(text : String): String {
            val parser =  SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            return formatter.format(parser.parse(text))
        }
        @SuppressLint("SimpleDateFormat")
        public fun convertYMD(text : String): String {
            val parser =  SimpleDateFormat("dd-MM-yyyy")
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            return formatter.format(parser.parse(text))
        }
    }

}
