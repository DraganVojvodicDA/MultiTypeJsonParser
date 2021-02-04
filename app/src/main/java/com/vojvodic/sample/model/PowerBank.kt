package com.vojvodic.sample.model

import com.vojvodic.sample.model.base.TYPE

data class PowerBank(
    val id: Long,
    val manufacturer: String,
    val model: String,
    val capacity: Int
) : Product(TYPE.POWER_BANK.getType())

