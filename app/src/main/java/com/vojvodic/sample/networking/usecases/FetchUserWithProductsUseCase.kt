package com.vojvodic.sample.networking.usecases

import com.vojvodic.sample.model.User
import com.vojvodic.sample.model.base.BaseResponse
import com.vojvodic.sample.model.base.Error
import com.vojvodic.sample.networking.RestApi
import com.vojvodic.sample.networking.RestCallback
import com.vojvodic.sample.networking.services.UserService

class FetchUserWithProductsUseCase : BaseObservable<FetchUserWithProductsUseCase.OnFetchUserWithProductsListener>() {

    interface OnFetchUserWithProductsListener {
        fun onUserWPFetchSuccess(user: BaseResponse<User>)
        fun onUserWPFetchFailed(description: String?)
    }

    fun fetchUser() {
        RestApi.service(UserService::class.java).getUserWithProducts()
            .enqueue(object : RestCallback<BaseResponse<User>>() {
                override fun onSuccess(response: BaseResponse<User>) {
                    notifySuccess(response)
                }

                override fun onError(description: String?, error: Error?) {
                    notifyFailed(description)
                }
            })
    }

    private fun notifySuccess(user: BaseResponse<User>) {
        for (listener in getListeners()) {
            listener.onUserWPFetchSuccess(user)
        }
    }

    private fun notifyFailed(description: String?) {
        for (listener in getListeners()) {
            listener.onUserWPFetchFailed(description)
        }
    }
}