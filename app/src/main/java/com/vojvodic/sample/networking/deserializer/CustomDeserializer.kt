package com.vojvodic.sample.networking.deserializer

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.vojvodic.sample.model.*
import com.vojvodic.sample.model.base.BaseResponse
import com.vojvodic.sample.model.base.TYPE
import java.lang.reflect.Type

class CustomDeserializer : JsonDeserializer<BaseResponse<*>> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): BaseResponse<*> {

        val gson = Gson()
        val jsonResponseObject = json?.asJsonObject
        val jsonRandomArray = jsonResponseObject?.getAsJsonArray("productsList")

        val baseResponse = gson.fromJson(json, typeOfT) as BaseResponse<*>
        if (jsonRandomArray == null) {
            return baseResponse
        }

        baseResponse.listOfProducts = mutableListOf()

        for (rand in jsonRandomArray) {

            val randomObject = rand.asJsonObject
            val random = when (randomObject.get("type").asString) {

                TYPE.CELLPHONE.getType() -> gson.fromJson(rand, Cellphone::class.java) as Cellphone
                TYPE.CHARGER.getType() -> gson.fromJson(rand, Charger::class.java) as Charger
                TYPE.HEADPHONE.getType() -> gson.fromJson(rand, Headphone::class.java) as Headphone
                TYPE.MEMORY_CARD.getType() -> gson.fromJson(rand, MemoryCard::class.java) as MemoryCard
                TYPE.POWER_BANK.getType() -> gson.fromJson(rand, PowerBank::class.java) as PowerBank
                else -> null
            }

            random?.let {
                baseResponse.listOfProducts?.add(it)
            }
        }
        return baseResponse
    }
}