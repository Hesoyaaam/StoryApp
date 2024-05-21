package com.example.storyapp.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.storyapp.data.UserRepository
import com.example.storyapp.data.remote.response.RegisterResponse
import com.example.storyapp.data.Result

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

    private val _register = MediatorLiveData<Result<RegisterResponse>>()
    val registerViewModel: LiveData<Result<RegisterResponse>> = _register

    fun register(name: String, email: String, password: String) {
        val liveData = repository.register(name, email, password)
        _register.addSource(liveData){ result ->
            _register.value = result
        }
    }
}