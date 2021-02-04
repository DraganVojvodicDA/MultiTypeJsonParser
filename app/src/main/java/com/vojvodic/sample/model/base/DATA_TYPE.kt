package com.vojvodic.sample.model.base

import android.annotation.SuppressLint

enum class DATA_TYPE {

    CELLPHONE,
    HEADPHONE,
    CHARGER,
    MEMORY_CARD,
    POWER_BANK;

    @SuppressLint("DefaultLocale")
    fun getType(): String {
        return this.name.toLowerCase()
    }
}