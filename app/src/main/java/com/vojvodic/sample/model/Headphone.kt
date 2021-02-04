package com.vojvodic.sample.model

import com.vojvodic.sample.model.base.DATA_TYPE

data class Headphone(
    val id: Long,
    val manufacturer: String,
    val model: String,
    val maxDb: String
) : Product(DATA_TYPE.HEADPHONE.getType())


