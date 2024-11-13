package com.example.coroutinesflow.network

import com.example.coroutinesflow.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private fun getInstance() : Retrofit{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
//            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val webRequest = getInstance().create(WebRequest::class.java)
}