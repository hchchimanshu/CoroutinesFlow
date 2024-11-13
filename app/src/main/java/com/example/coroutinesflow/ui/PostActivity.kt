package com.example.coroutinesflow.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.coroutinesflow.R
import com.example.coroutinesflow.network.RetrofitBuilder
import com.example.coroutinesflow.repository.PostRepository
import com.example.coroutinesflow.utils.Resource

class PostActivity : AppCompatActivity() {

    private val TAG = "PostActivity"
    private val mViewModel : PostViewModel by viewModels {
        PostViewModelFactory(
            PostRepository(RetrofitBuilder.webRequest)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel.post.observe(this){
            when(it){
                is Resource.Failed -> {
                    Log.d(TAG, "Failed : ${it.message}")
                    Toast.makeText(this, "Failed : ${it.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    Log.d(TAG, "load")
                    Toast.makeText(this, "load", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    Log.d(TAG, "Success : ${it.data}")
                    Toast.makeText(this, "Success : ${it.data}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        mViewModel.getPost()

    }
}