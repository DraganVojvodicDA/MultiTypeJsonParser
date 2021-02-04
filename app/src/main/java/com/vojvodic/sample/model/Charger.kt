package com.vojvodic.sample.model

import com.vojvodic.sample.model.base.DATA_TYPE

data class Charger(
    val id: Long,
    val manufacturer: String,
    val model: String,
    val chargerType: String
) : Product(DATA_TYPE.CHARGER.getType())
