package com.vojvodic.sample.model

import com.vojvodic.sample.model.base.DATA_TYPE

data class PowerBank(
    val id: Long,
    val manufacturer: String,
    val model: String,
    val capacity: Int
) : Product(DATA_TYPE.POWER_BANK.getType())