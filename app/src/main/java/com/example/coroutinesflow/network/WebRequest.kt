package com.example.coroutinesflow.network

import com.example.coroutinesflow.model.Post
import retrofit2.http.GET

interface WebRequest {

    @GET("posts")
    suspend fun getPostData() : List<Post>
}