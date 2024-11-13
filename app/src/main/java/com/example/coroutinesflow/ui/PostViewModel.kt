package com.example.coroutinesflow.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinesflow.model.Post
import com.example.coroutinesflow.repository.PostRepository
import com.example.coroutinesflow.utils.Resource
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val _posts : MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    val post : LiveData<Resource<List<Post>>> get() = _posts

    fun getPost() = viewModelScope.launch {
        postRepository.getPosts().collect(){
            _posts.value = it
        }
    }
}