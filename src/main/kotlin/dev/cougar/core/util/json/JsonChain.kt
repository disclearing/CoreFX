package dev.cougar.core.util.json

import com.google.gson.JsonElement
import com.google.gson.JsonObject

@Suppress("unused")
class JsonChain {
    private val json = JsonObject()
    fun addProperty(property: String, value: String): JsonChain {
        json.addProperty(property, value)
        return this
    }

    fun addProperty(property: String, value: Number): JsonChain {
        json.addProperty(property, value)
        return this
    }

    fun addProperty(property: String, value: Boolean): JsonChain {
        json.addProperty(property, value)
        return this
    }

    fun addProperty(property: String, value: Char): JsonChain {
        json.addProperty(property, value)
        return this
    }

    fun add(property: String, element: JsonElement): JsonChain {
        json.add(property, element)
        return this
    }

    fun get(): JsonObject {
        return json
    }
}