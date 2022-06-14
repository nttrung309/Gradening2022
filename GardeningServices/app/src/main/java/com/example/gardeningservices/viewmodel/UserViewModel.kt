package com.example.gardeningservices.viewmodel

import android.widget.Toast
import androidx.lifecycle.*
import com.example.gardeningservices.SignInActivity
import com.example.gardeningservices.model.CRUDresponse
import com.example.gardeningservices.network.user.UserResponse
import com.example.gardeningservices.utilities.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import okhttp3.Dispatcher

class UserViewModel: ViewModel() {

    private var userResponse: UserResponse = UserResponse()

    fun login(username: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = userResponse.login(username,password)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun updateProfile(idUser:String,name: String,date:String, gender: String,telephone:String,email:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = userResponse.updateProfile(idUser,name,date,gender,telephone,email)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getProfile(idUser:String,name: String,date:String, gender: String,telephone:String,email:String)= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = userResponse.updateProfile(
                        idUser,
                        name,
                        date,
                        gender,
                        telephone,
                        email
                    )
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getUserById(idUser: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = userResponse.getUserById(idUser)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}