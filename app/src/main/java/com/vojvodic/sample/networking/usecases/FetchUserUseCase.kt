package com.vojvodic.sample.networking.usecases

import com.vojvodic.sample.model.User
import com.vojvodic.sample.model.base.BaseResponse
import com.vojvodic.sample.model.base.Error
import com.vojvodic.sample.networking.RestApi
import com.vojvodic.sample.networking.RestCallback
import com.vojvodic.sample.networking.services.UserService

class FetchUserUseCase : BaseObservable<FetchUserUseCase.OnUserFetchedListener>() {

    interface OnUserFetchedListener {
        fun onUserFetchSuccess(user: BaseResponse<User>)
        fun onUserFetchFailed(description: String?)
    }

    fun fetchUser() {
        RestApi.service(UserService::class.java).getUser()
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
            listener.onUserFetchSuccess(user)
        }
    }

    private fun notifyFailed(description: String?) {
        for (listener in getListeners()) {
            listener.onUserFetchFailed(description)
        }
    }
}