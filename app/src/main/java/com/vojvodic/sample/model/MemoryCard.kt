package com.vojvodic.sample.model

import com.vojvodic.sample.model.base.TYPE

data class MemoryCard(
    val id: Long,
    val manufacturer: String,
    val capacity: Int
) : Product(TYPE.MEMORY_CARD.getType())

