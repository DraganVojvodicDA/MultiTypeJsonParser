package com.vojvodic.sample.model.base

import java.io.Serializable

data class Error(
    val errorCode: Int,
    val message: String
) : Serializable
