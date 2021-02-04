package com.vojvodic.sample.utils

import java.io.Serializable

@Suppress("UNCHECKED_CAST")
class CastUtil {

    companion object {
        fun <T> cast(serializable: Serializable): T {
            return serializable as T
        }
    }
}