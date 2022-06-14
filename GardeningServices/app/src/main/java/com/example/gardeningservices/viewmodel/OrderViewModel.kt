package com.example.gardeningservices.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.gardeningservices.network.order.OrderResponse
import com.example.gardeningservices.utilities.Resource
import kotlinx.coroutines.Dispatchers

class OrderViewModel:ViewModel() {
    private  var orderResponse: OrderResponse = OrderResponse()

    fun postOrder(idUser: Int,
                  idAddress: Int,
                  date: String,
                  state: String,
                  provisional_money: Int,
                  shipping: Int,
                  total: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = orderResponse.postOrder(idUser, idAddress, date, state, provisional_money, shipping, total)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun postOrderDetail(idOrder: Int,
                        idProduct: Int,
                        quantity: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = orderResponse.postOrderDetail(idOrder, idProduct, quantity)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getOrder(idUser: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = orderResponse.getOrder(idUser)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getItemsOrder(idOrder: List<String>) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = orderResponse.getItemsOrder(idOrder)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getOrderById(idOrder: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = orderResponse.getOrderById(idOrder)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getProductByOrder(idProduct: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = orderResponse.getProductByOrder(idProduct)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getIdProductByOrder(idOrder: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = orderResponse.getIdProductByOrder(idOrder)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}