package com.vojvodic.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.vojvodic.sample.model.*
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
        val fetchUserProdBtn: Button = findViewById(R.id.fetchUserWithProducts)

        fetchUserBtn.setOnClickListener {
            fetchUser()
        }

        fetchUserProdBtn.setOnClickListener {
            userWithProducts()
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

    private fun userWithProducts() {
        RestApi.service(UserService::class.java).getUserWithProducts()
            .enqueue(object : RestCallback<BaseResponse<User>>() {
                override fun onSuccess(response: BaseResponse<User>) {

                    Log.i(TAG, "************************************************")
                    Log.i(TAG, "onSuccess: " + response.data.name)
                    Log.i(TAG, "onSuccess: " + response.data.email)
                    Log.i(TAG, "************************************************")

                    Log.i(TAG, "List of products size: " + response.listOfProducts?.size)

                    response.listOfProducts?.let { products ->
                        for (product in products) {
                            when (product) {
                                is Cellphone -> Log.i(TAG, "Cellphone: $product")
                                is Headphone -> Log.i(TAG, "Headphone: $product")
                                is Charger -> Log.i(TAG, "Charger: $product")
                                is MemoryCard -> Log.i(TAG, "MemoryCard: $product")
                            }
                        }
                    }
                }

                override fun onError(description: String?, error: Error?) {
                    Log.i(TAG, "onError: $description")
                }

            })


    }

}