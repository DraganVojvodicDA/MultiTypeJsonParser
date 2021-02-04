package com.vojvodic.sample.model

import com.vojvodic.sample.model.base.TYPE

data class Charger(
    val id: Long,
    val manufacturer: String,
    val model: String,
    val chargerType: String
) : Product(TYPE.CHARGER.getType())
