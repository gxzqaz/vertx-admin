package com.aze.admin.module

import com.aze.admin.common.constant.MessageConst.LOCAL_REDIS_GET
import com.aze.admin.common.extend.awaitGet
import io.lettuce.core.RedisClient
import io.lettuce.core.RedisURI
import io.lettuce.core.api.StatefulRedisConnection
import io.vertx.core.json.JsonArray
import io.vertx.core.logging.LoggerFactory
import io.vertx.kotlin.coroutines.CoroutineVerticle
import kotlinx.coroutines.launch
import java.time.Duration


class RedisVerticle : CoroutineVerticle() {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    private var redisClient: RedisClient

    private var connection: StatefulRedisConnection<String, String>

    init {
        val redisURI =
            RedisURI.builder().withHost("localhost").withPort(6380).withTimeout(Duration.ofSeconds(10)).build()
        redisURI.password = "fUj@%h!kMHajrEhb".toCharArray()
        redisURI.database = 0
        redisClient = RedisClient.create(redisURI);
        // <3> 创建线程安全的连接, lettuce复用了连接, 所以只要程序销毁的时候关闭连接即可
        connection = redisClient.connect()
    }


    override suspend fun start() {
        // 使用event bus来解耦
        val asyncCommands = connection.async()
        val eventBus = vertx.eventBus()
        val consumer = eventBus.consumer<String>(LOCAL_REDIS_GET)
        consumer.handler {
            val key = it.body()
            logger.info(key)
            launch {
                val res = awaitGet(asyncCommands.get("user"))
                println(this@RedisVerticle)
                it.reply(JsonArray(res))
            }
        }
    }

    override suspend fun stop() {
        connection.close()
    }
}
