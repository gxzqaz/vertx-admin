package com.aze.admin.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

object JsonUtil {

    val objectMapper = ObjectMapper()

    private val camelCastMapper = ObjectMapper()

    init {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModules(JavaTimeModule(), Jdk8Module())

        // 下划线 => 驼峰转换
        camelCastMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
        camelCastMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        camelCastMapper.propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
        camelCastMapper.registerModules(JavaTimeModule(), Jdk8Module())
    }

    fun write(any: Any?): String? {
        if (any == null) {
            return any
        }
        return objectMapper.writeValueAsString(any)
    }

    fun readToMap(any: String?): Map<String, Any> {
        if (any == null) {
            return mapOf()
        }
        return objectMapper.readValue(any, jacksonTypeRef<Map<String, Any>>())
    }

    fun <T> readByType(any: String?, type: TypeReference<T>): T? {
        if (any == null) return null
        return objectMapper.readValue(any, type)
    }

    fun <T> readByType(bytes: ByteArray?, type: TypeReference<T>): T? {
        if (bytes == null) return null
        return objectMapper.readValue(bytes, type)
    }

    fun <T> camelConvert(str: String?, type: TypeReference<T>): T? {
        if (isEmpty(str)) return null
        return objectMapper.readValue(str, type)
    }
}
