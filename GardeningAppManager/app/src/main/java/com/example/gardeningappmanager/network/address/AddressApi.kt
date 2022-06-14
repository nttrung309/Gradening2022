package com.example.gardeningappmanager.network.address

import com.example.gardeningappmanager.model.Address
import com.example.gardeningappmanager.model.CRUDresponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AddressApi {
    @FormUrlEncoded
    @POST("get_address.php")
    suspend fun getAddress(
        @Field("idUser") idUser:String
    ): List<Address>

    @FormUrlEncoded
    @POST("get_address_by_id.php")

    suspend fun getAddressById(@Field("idAddress") idAddress: Int): Address

    @FormUrlEncoded
    @POST("post_delete_address.php")
    fun deleteAddress(
        @Field("id") id:String
    ): Call<CRUDresponse>

    @FormUrlEncoded
    @POST("post_new_address.php")
    suspend fun createAddress(
        @Field("idUser") idUser:String,
        @Field("address_name") address_name:String,
        @Field("address_number") address_number:String,
        @Field("address_line") address_line :String,
        @Field("province") province:String,
        @Field("district") district:String,
        @Field("ward") ward:String
    ): CRUDresponse

    @FormUrlEncoded
    @POST("post_update_edit_address.php")
    suspend fun updateAddress(
        @Field("id") id:String,
        @Field("address_name") address_name:String,
        @Field("address_number") address_number:String,
        @Field("address_line") address_line :String,
        @Field("province") province:String,
        @Field("district") district:String,
        @Field("ward") ward:String
    ): CRUDresponse
}