package com.example.demoforjetpack.apicall

import com.example.demoforjetpack.apicall.datamodel.Post
import com.example.demoforjetpack.apicall.datamodel.PostPic
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("photos")
    suspend fun getPhotos(): List<PostPic>

}