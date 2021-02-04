package com.vojvodic.sample.networking

import com.google.gson.GsonBuilder
import com.vojvodic.sample.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApi {

    private val retrofit: Retrofit
        get() {

            val gsonBuilder = GsonBuilder()
                .setLenient()
                .create()

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
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