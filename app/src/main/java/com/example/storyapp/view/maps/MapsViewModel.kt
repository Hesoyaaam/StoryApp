package com.example.storyapp.view.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.storyapp.data.Result
import com.example.storyapp.data.UserRepository
import com.example.storyapp.data.pref.UserModel
import com.example.storyapp.data.remote.response.ListStoryItem

class MapsViewModel(private val repository: UserRepository): ViewModel() {

    private val _mapsViewModel = MediatorLiveData<Result<List<ListStoryItem>>>()
    val mapsLocation : LiveData<Result<List<ListStoryItem>>> = _mapsViewModel

    fun getStoryLocation(token: String) {
        val liveData = repository.uploadStoryWithLocation(token)
        _mapsViewModel.addSource(liveData) { result ->
            _mapsViewModel.value = result
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}