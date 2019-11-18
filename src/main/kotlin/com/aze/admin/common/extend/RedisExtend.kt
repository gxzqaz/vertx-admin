package com.aze.admin.common.extend

import io.lettuce.core.RedisFuture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

suspend fun <T> awaitGet(redisFuture: RedisFuture<T>): T {
    return withContext(Dispatchers.IO) {
        redisFuture.get(5, TimeUnit.SECONDS)
    }
}
