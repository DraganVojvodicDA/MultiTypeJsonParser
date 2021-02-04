package com.vojvodic.sample.model.base

import com.vojvodic.sample.model.Product
import java.io.Serializable

data class BaseResponse<DATA>(
    val data: DATA,
    val listOfProducts: MutableList<Product>
) : Serializable
