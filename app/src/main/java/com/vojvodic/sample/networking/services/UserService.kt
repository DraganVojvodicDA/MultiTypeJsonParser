package com.vojvodic.sample.networking.services

import com.vojvodic.sample.common.Constants
import com.vojvodic.sample.model.User
import com.vojvodic.sample.model.base.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserService {

    @Headers(Constants.CONTENT_TYPE_APP_JSON, Constants.ACCEPT)
    @GET(Constants.USER)
    fun getUser(): Call<BaseResponse<User>>

    @Headers(Constants.CONTENT_TYPE_APP_JSON, Constants.ACCEPT)
    @GET(Constants.USER_WITH_PRODUCTS)
    fun getUserWithProducts(): Call<BaseResponse<User>>
}