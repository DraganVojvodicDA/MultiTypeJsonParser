package com.vojvodic.sample.model

import com.vojvodic.sample.model.base.TYPE

data class Headphone(
    val id: Long,
    val manufacturer: String,
    val model: String,
    val maxDb: String
) : Product(TYPE.HEADPHONE.getType())


