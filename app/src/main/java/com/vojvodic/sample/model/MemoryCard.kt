package com.vojvodic.sample.model

import com.vojvodic.sample.model.base.DATA_TYPE

data class MemoryCard(
    val id: Long,
    val manufacturer: String,
    val capacity: Int
) : Product(DATA_TYPE.MEMORY_CARD.getType())

