package com.example.gardeningservices.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.gardeningservices.model.Address
import com.example.gardeningservices.network.address.AddressRepository
import com.example.gardeningservices.utilities.Resource
import kotlinx.coroutines.Dispatchers

class AddressViewModel:ViewModel() {

    private  var addressRepository = AddressRepository()

    fun getListAddress(idUser: String)  = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = addressRepository.getListAddress(idUser)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun updateAddress(id:String,addressName:String,addressNumber:String,addressLine:String,Province:String,District:String,Ward:String)=liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = addressRepository.updateAddress(id,addressName,addressNumber,addressLine,Province,District,Ward)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getAddressById(idAddress: Int)  = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = addressRepository.getAddressById(idAddress)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun deleteAddress(id: String) {
        addressRepository.deleteAddress(id)
    }
    fun createNewAddress(idUser:String,addressName:String,addressNumber:String,addressLine:String,Province:String,District:String,Ward:String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = addressRepository.createNewAddress(idUser,addressName,addressNumber,addressLine,Province,District,Ward)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}