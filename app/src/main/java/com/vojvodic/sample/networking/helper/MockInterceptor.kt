package com.vojvodic.sample.networking.helper

import com.vojvodic.sample.BuildConfig
import com.vojvodic.sample.common.Constants
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {

            val uri = chain.request().url.toUri().toString()

            val responseString = when {
                uri.endsWith(Constants.USER) -> getUser
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toByteArray()
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }
}

const val getUser = "{\"data\":{\"id\":1,\"name\":\"John\",\"type\":\"user\",\"email\":\"johndoe@gmail.com\",\"lastName\":\"John\",\"phoneNumber\":\"1232123213\"}}"
