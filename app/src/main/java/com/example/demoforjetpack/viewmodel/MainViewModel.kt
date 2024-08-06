package com.example.demoforjetpack.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoforjetpack.apicall.RetrofitInstance
import com.example.demoforjetpack.apicall.datamodel.Post
import com.example.demoforjetpack.apicall.datamodel.PostPic
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _posts = mutableStateOf<List<Post>>(emptyList())
    private val _postspic = mutableStateOf<List<PostPic>>(emptyList())
    val posts: State<List<Post>> = _posts
    val postspic: State<List<PostPic>> = _postspic

    fun getPosts() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPosts()
                if (response.isNotEmpty()) {
                    _posts.value = response
                }
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }

    fun getPhotos() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPhotos()
                if (response.isNotEmpty()) {
                    _postspic.value = response
                }
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }
}