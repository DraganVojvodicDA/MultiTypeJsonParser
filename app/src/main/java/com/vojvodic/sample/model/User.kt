package com.vojvodic.sample.model

import java.io.Serializable

data class User(
    val id: Long,
    val name: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String

) : Serializable