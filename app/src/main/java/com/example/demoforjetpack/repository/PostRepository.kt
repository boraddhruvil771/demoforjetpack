package com.example.demoforjetpack.repository

import com.example.demoforjetpack.apicall.RetrofitInstance
import com.example.demoforjetpack.apicall.datamodel.Post
import com.example.demoforjetpack.apicall.datamodel.PostPic

class PostRepository {
    suspend fun getPosts(): List<Post> {
        return RetrofitInstance.api.getPosts()
    }

    suspend fun getPhotos(): List<PostPic> {
        return RetrofitInstance.api.getPhotos()
    }
}