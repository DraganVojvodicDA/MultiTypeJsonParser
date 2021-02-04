package com.vojvodic.sample.networking

import com.google.gson.GsonBuilder
import com.vojvodic.sample.common.Constants
import com.vojvodic.sample.model.base.BaseResponse
import com.vojvodic.sample.networking.deserializer.CustomDeserializer
import com.vojvodic.sample.networking.helper.MockInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApi {

    private val retrofit: Retrofit
        get() {

            val gsonBuilder = GsonBuilder()
                .registerTypeAdapter(BaseResponse::class.java, CustomDeserializer())
                .setLenient()
                .create()

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val mockInterceptor = MockInterceptor()

            val client = OkHttpClient.Builder()
                .addNetworkInterceptor(mockInterceptor)
                .build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .build()
        }

    fun <SERVICE> service(interfaceService: Class<SERVICE>): SERVICE {
        return retrofit.create(interfaceService)
    }
}