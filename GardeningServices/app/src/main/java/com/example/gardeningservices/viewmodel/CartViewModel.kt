package com.example.gardeningservices.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.gardeningservices.model.Cart
import com.example.gardeningservices.model.CartDetail
import com.example.gardeningservices.model.Products
import com.example.gardeningservices.network.cart.CartRepository
import com.example.gardeningservices.utilities.Resource
import kotlinx.coroutines.Dispatchers

class CartViewModel: ViewModel() {

    private  var cartResponse: CartRepository = CartRepository()
    private var listProduct: MutableLiveData<List<Products>> = MutableLiveData()
    private  var listCartDetail: MutableLiveData<List<CartDetail>> = MutableLiveData()

    fun  getListProduct(): MutableLiveData<List<Products>> {
        return listProduct
    }
    fun setListProduct(list: List<Products>) {
        listProduct.value = list
    }
    fun  getListCartDetail(): MutableLiveData<List<CartDetail>> {
        return listCartDetail
    }
    fun setListCartDetail(list: List<CartDetail>) {
        listCartDetail.value = list
    }
    fun getCartByUser(idUser: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = cartResponse.getCartByUser(idUser)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getCartDetailByCart(idCart: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = cartResponse.getCartDetailByCart(idCart)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun postUpdateQuantityProductDeleteItem(idProduct: Int, quantity: Int) {
        cartResponse.postUpdateQuantityProductDeleteItem(idProduct, quantity)
    }

    fun postDeleteCartDetail(idCartDetail: Int) {
        cartResponse.postDeleteCartDetail(idCartDetail)
    }
    fun postChangeQuantityItem(idCartDetail: Int,idProduct: Int, quantity: Int, quantityUpdate: Int) {
        cartResponse.postChangeQuantityItem(idCartDetail, idProduct, quantity, quantityUpdate)
    }
    fun postAddProductToCart(idCart: Int, idProduct: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = cartResponse.postAddProductToCart(idCart, idProduct)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun postUpdateTotalCart(total: Int, idCart: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = cartResponse.postUpdateTotalCart(total, idCart)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun postUpdateStateCart(idCart: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = cartResponse.postUpdateStateCart(idCart)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}