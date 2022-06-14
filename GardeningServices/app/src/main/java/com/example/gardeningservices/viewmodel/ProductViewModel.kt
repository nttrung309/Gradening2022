package com.example.gardeningservices.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.gardeningservices.network.products.ProductRepository
import com.example.gardeningservices.utilities.Resource
import kotlinx.coroutines.Dispatchers

class ProductViewModel: ViewModel() {

    private var productRepository: ProductRepository = ProductRepository()

    fun getProductById(idProduct: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = productRepository.getProductById(idProduct)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getProductDetail(idProduct: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = productRepository.getProductDetail(idProduct)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun postUpdateQuantityProduct(quantity:Int,idProduct: Int) {
        productRepository.postUpdateQuantityProduct(quantity,idProduct)
    }

    fun getProductListByCartDetail(idProduct: List<String>) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = productRepository.getProductListByCartDetail(idProduct)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}