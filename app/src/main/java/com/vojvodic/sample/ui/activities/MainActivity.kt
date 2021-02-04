package com.vojvodic.sample.ui.activities

import android.util.Log
import android.widget.Button
import com.vojvodic.sample.R
import com.vojvodic.sample.model.*
import com.vojvodic.sample.model.base.BaseResponse
import com.vojvodic.sample.networking.usecases.FetchUserUseCase
import com.vojvodic.sample.networking.usecases.FetchUserWithProductsUseCase
import com.vojvodic.sample.utils.CastUtil

const val TAG = "SAM_TAG"

class MainActivity : BaseActivity(), FetchUserUseCase.OnUserFetchedListener,
    FetchUserWithProductsUseCase.OnFetchUserWithProductsListener {


    private lateinit var fetchUserUserCase: FetchUserUseCase
    private lateinit var fetchUserWithProductsUseCase: FetchUserWithProductsUseCase


    override fun provideLayout(): Int {
        return R.layout.activity_main
    }

    override fun layoutReady() {

        fetchUserUserCase = getAppContainer().fetchUserUserCase()
        fetchUserWithProductsUseCase = getAppContainer().fetchUserWithProductsUseCase()


        val fetchUserBtn: Button = findViewById(R.id.fetchUser)
        val fetchUserProdBtn: Button = findViewById(R.id.fetchUserWithProducts)

        fetchUserBtn.setOnClickListener {
            fetchUserUserCase.fetchUser()
        }

        fetchUserProdBtn.setOnClickListener {
            fetchUserWithProductsUseCase.fetchUser()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: REGISTER")
        fetchUserUserCase.registerListener(this)
        fetchUserWithProductsUseCase.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: UNREGISTER")
        fetchUserUserCase.unregisterListener(this)
        fetchUserWithProductsUseCase.unregisterListener(this)
    }

    //region **** FETCH USER  ****
    override fun onUserFetchSuccess(user: BaseResponse<User>) {
        Log.i(TAG, "onUserFetchSuccess: $user")
    }

    override fun onUserFetchFailed(description: String?) {
        Log.i(TAG, "onUserFetchFailed: $description")
    }
    //endregion

    //region **** FETCH USER WITH PRODUCTS ****
    override fun onUserWPFetchSuccess(user: BaseResponse<User>) {

        Log.i(TAG, "************************************************")
        Log.i(TAG, "onSuccess: " + user.data.name)
        Log.i(TAG, "onSuccess: " + user.data.email)
        Log.i(TAG, "************************************************")

        Log.i(TAG, "List of products size: " + user.listOfProducts?.size)

        user.listOfProducts?.let { products ->
            for (product in products) {
                when (product) {
                    is Cellphone -> {
                        val cellphone = CastUtil.cast<Cellphone>(product)
                        Log.i(TAG, "Cellphone: $cellphone" + ";   Product type: " + cellphone.type)
                    }
                    is Headphone -> Log.i(TAG, "Headphone: $product")
                    is Charger -> Log.i(TAG, "Charger: $product")
                    is MemoryCard -> Log.i(TAG, "MemoryCard: $product")
                }
            }
        }
    }

    override fun onUserWPFetchFailed(description: String?) {
        Log.i(TAG, "onUserWPFetchFailed: $description")
    }
    //endregion
}