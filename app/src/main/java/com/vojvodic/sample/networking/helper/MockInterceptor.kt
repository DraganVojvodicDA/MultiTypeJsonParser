package com.vojvodic.sample.networking.helper

import com.vojvodic.sample.BuildConfig
import com.vojvodic.sample.common.Constants
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {

            val uri = chain.request().url.toUri().toString()

            val responseString = when {
                uri.endsWith(Constants.USER) -> getUser
                uri.endsWith(Constants.USER_WITH_PRODUCTS) -> getUserWithProducts
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

const val getUser =
    "{\"data\":{\"id\":1,\"name\":\"John\",\"type\":\"user\",\"email\":\"johndoe@gmail.com\",\"lastName\":\"John\",\"phoneNumber\":\"1232123213\"}}"

const val getUserWithProducts =
    "{\"data\":{\"id\":1,\"name\":\"John\",\"type\":\"user\",\"email\":\"johndoe@gmail.com\",\"lastName\":\"John\",\"phoneNumber\":\"1232123213\"},\"productsList\":[{\"id\":2343,\"type\":\"cellphone\",\"model\":\"Xperia\",\"displaySize\":6.3,\"manufacturer\":\"Sony\",\"batteryCapacity\":\"4000\"},{\"id\":554545,\"type\":\"charger\",\"model\":\"m2\",\"chargerType\":\"type C\",\"manufacturer\":\"Xiaomi\"},{\"id\":897865,\"type\":\"headphone\",\"maxDb\":\"79dB\",\"model\":\"cbm-2343\",\"manufacturer\":\"Samsung\"},{\"id\":12354,\"type\":\"memory_card\",\"capacity\":20000,\"manufacturer\":\"Transcend\"}]}"