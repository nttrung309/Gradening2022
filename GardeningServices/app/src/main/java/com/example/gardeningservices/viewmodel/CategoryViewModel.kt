package com.example.gardeningservices.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gardeningservices.adapter.CategoryAdapter
import com.example.gardeningservices.model.Category
import com.example.gardeningservices.network.ApiUtils
import com.example.gardeningservices.network.category.CategoryRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel: ViewModel() {

    var listCategory: MutableLiveData<List<Category>> = MutableLiveData()
    var listCategoryHome: MutableLiveData<List<Category>> = MutableLiveData()

    private  var categoryRepository = CategoryRepository()

    fun CallAllCategoryApi() {
        categoryRepository.getListCategoryApi(listCategory)
    }
    fun CallCategoryHome() {
        categoryRepository.getCategoryHomeApi(listCategoryHome)
    }
}