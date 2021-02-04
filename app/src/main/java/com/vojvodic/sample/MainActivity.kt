package com.vojvodic.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.vojvodic.sample.model.User
import com.vojvodic.sample.model.base.BaseResponse
import com.vojvodic.sample.model.base.Error
import com.vojvodic.sample.networking.RestApi
import com.vojvodic.sample.networking.RestCallback
import com.vojvodic.sample.networking.services.UserService

const val TAG = "SAM_TAG"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fetchUserBtn: Button = findViewById(R.id.fetchUser)

        fetchUserBtn.setOnClickListener {
            fetchUser()
        }
    }

    private fun fetchUser() {
        RestApi.service(UserService::class.java).getUser()
            .enqueue(object : RestCallback<BaseResponse<User>>() {
                override fun onSuccess(response: BaseResponse<User>) {
                    Log.i(TAG, "onSuccess: " + response.data.toString())
                }

                override fun onError(description: String?, error: Error?) {
                    Log.i(TAG, "onError: " + description)
                }
            })
    }
}