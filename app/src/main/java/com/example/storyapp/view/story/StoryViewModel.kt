package com.example.storyapp.view.story

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.storyapp.data.Result
import com.example.storyapp.data.UserRepository
import com.example.storyapp.data.pref.UserModel
import com.example.storyapp.data.remote.response.UploadStoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoryViewModel(private val repository: UserRepository) : ViewModel() {

    private val _uploadViewModel = MediatorLiveData<Result<UploadStoriesResponse>>()
    val uploadViewModel: LiveData<Result<UploadStoriesResponse>> = _uploadViewModel

    fun uploadStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody,
        currentLocation: Location?
    ) {
        val liveData = repository.uploadStory(token, file, description, currentLocation)
        _uploadViewModel.addSource(liveData) { result ->
            _uploadViewModel    .value = result
        }
    }

    fun getUser(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}