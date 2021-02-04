package com.vojvodic.sample.networking

import android.util.Log
import com.vojvodic.sample.model.base.Error
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "REST_TAG"

abstract class RestCallback<RESPONSE> : Callback<RESPONSE> {

    override fun onResponse(call: Call<RESPONSE>, response: Response<RESPONSE>) {

        if (response.isSuccessful && response.code() >= 200 && response.code() <= 300) {
            response.body()?.let {
                onSuccess(it)
            }
        } else if (!response.isSuccessful && response.errorBody() != null) {

            Log.i(TAG, "Error body" + response.errorBody().toString())
            val error = Error(response.code(), response.message())
            onError(response.message(), error)

        } else {
            onError(response.message(), null)
        }
    }

    override fun onFailure(call: Call<RESPONSE>, t: Throwable?) {
        Log.i(TAG, "onFailure: " + t?.message)
        onError(t?.message, null)
    }

    protected abstract fun onSuccess(response: RESPONSE)
    protected abstract fun onError(description: String?, error: Error?)
}