package com.example.coroutinesflow.repository

import com.example.coroutinesflow.model.Post
import com.example.coroutinesflow.network.WebRequest
import com.example.coroutinesflow.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import java.util.concurrent.Flow

class PostRepository(private val webRequest: WebRequest) {

    fun getPosts() = flow<Resource<List<Post>>>{

        emit(Resource.loading())
        val posts = webRequest.getPostData()
        emit(Resource.success(posts))
    }.catch {
        emit(Resource.failed(it.message!!))
    }.flowOn(Dispatchers.IO)

}