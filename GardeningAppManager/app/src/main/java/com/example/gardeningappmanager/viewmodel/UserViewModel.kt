package com.example.gardeningappmanager.viewmodel

import androidx.lifecycle.*
import com.example.gardeningappmanager.network.user.UserRepository
import com.example.gardeningappmanager.Utils.Resource
import kotlinx.coroutines.Dispatchers

class UserViewModel:ViewModel() {

    private var userRepository: UserRepository = UserRepository()

    fun getUser() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = userRepository.getUser()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}