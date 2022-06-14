package com.example.gardeningservices.network.services

import androidx.lifecycle.MutableLiveData
import com.example.gardeningservices.model.Services
import com.example.gardeningservices.network.ApiUtils
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ServicesRepository {
    private val servicesApi = ApiUtils.createServicesApi()

    fun getListServicesApi(liveData: MutableLiveData<List<Services>>) {
        servicesApi.getAllServices().enqueue(object : Callback<List<Services>> {
            override fun onFailure(call: Call<List<Services>>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<List<Services>>,
                response: Response<List<Services>>
            ) {
                liveData.value = response.body()
            }
        })
    }
    fun getServicesHome(liveData: MutableLiveData<List<Services>>) {
        servicesApi.getServicesHome().enqueue(object : Callback<List<Services>> {
            override fun onFailure(call: Call<List<Services>>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<List<Services>>,
                response: Response<List<Services>>
            ) {
                liveData.value = response.body()
            }
        })
    }
    fun getServiceDetail(liveData: MutableLiveData<Services>){
        servicesApi.getServiceDetail().enqueue(object :Callback<Services>{
            override fun onFailure(call: Call<Services>, t: Throwable) {
            }
            override fun onResponse(call: Call<Services>, response: Response<Services>) {
                liveData.value= response.body()
            }
        })
    }
}
