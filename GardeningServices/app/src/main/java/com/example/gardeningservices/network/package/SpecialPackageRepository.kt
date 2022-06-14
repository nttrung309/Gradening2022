package com.example.gardeningservices.network.`package`

import androidx.lifecycle.MutableLiveData
import com.example.gardeningservices.model.SpecialPackage
import com.example.gardeningservices.network.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpecialPackageRepository {

    private val packageApi = ApiUtils.getPackageApi()

    fun getListPackageApi(liveData: MutableLiveData<List<SpecialPackage>>) {
        // Your Api Call with response callback
        packageApi.getAllSpecialPackage().enqueue(object: Callback<List<SpecialPackage>> {
            override fun onFailure(call: Call<List<SpecialPackage>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<SpecialPackage>>,
                response: Response<List<SpecialPackage>>
            ) {
                liveData.value = response.body()
            }
        })
    }
}