package com.example.gardeningservices.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gardeningservices.adapter.SpecialPackageAdapter
import com.example.gardeningservices.model.SpecialPackage
import com.example.gardeningservices.network.ApiUtils
import com.example.gardeningservices.network.`package`.SpecialPackageRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpecialPackageViewModel: ViewModel() {

    var listPackage: MutableLiveData<List<SpecialPackage>> = MutableLiveData()

    private  var categoryRepository = SpecialPackageRepository()

    fun CallAllSpecialPackageApi() {
        categoryRepository.getListPackageApi(listPackage)
    }
}