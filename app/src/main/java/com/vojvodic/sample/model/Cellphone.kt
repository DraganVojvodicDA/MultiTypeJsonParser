package com.vojvodic.sample.model

import com.vojvodic.sample.model.base.DATA_TYPE

data class Cellphone(
    val id: Long,
    val manufacturer: String,
    val model: String,
    val displaySize: Double,
    val batteryCapacity: Int
) : Product(DATA_TYPE.CELLPHONE.getType())
