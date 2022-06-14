package com.example.gardeningappmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.gardeningappmanager.Utils.Resource
import com.example.gardeningappmanager.network.product.ProductRepository
import kotlinx.coroutines.Dispatchers

class ProductViewModel : ViewModel() {

    private var productRepository: ProductRepository = ProductRepository()

    fun getProduct() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = productRepository.getProduct()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun postUpdateProduct(
            quantity: Int,
            price: Int,
            name: String,
            idProduct: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = productRepository.postUpdateProduct(quantity,price,name,idProduct)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}