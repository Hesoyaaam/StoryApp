package com.example.storyapp.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.storyapp.data.UserRepository
import com.example.storyapp.data.remote.response.LoginResponse
import com.example.storyapp.data.Result


class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private val _login = MediatorLiveData<Result<LoginResponse>>()
    val loginViewModel: LiveData<Result<LoginResponse>> = _login

    fun login(email: String, password: String) {
        val liveData = repository.login(email, password)
        _login.addSource(liveData) { result ->
            _login.value = result
        }
    }
}