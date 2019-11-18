package com.aze.admin

import com.aze.admin.common.constant.Constants
import com.aze.admin.module.RedisVerticle
import com.aze.admin.util.readFile
import com.aze.admin.verticle.GlobalVerticle
import com.aze.admin.verticle.LoginVerticle
import com.aze.admin.verticle.MockVerticle
import com.aze.admin.verticle.UserVerticle
import com.fasterxml.jackson.annotation.JsonInclude
import io.vertx.core.json.JsonObject
import io.vertx.core.json.jackson.DatabindCodec
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.jdbc.JDBCClient
import io.vertx.ext.sql.SQLClient
import io.vertx.ext.web.Router
import io.vertx.kotlin.core.deployVerticleAwait
import io.vertx.kotlin.core.http.listenAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle


class MainVerticle : CoroutineVerticle() {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    private lateinit var sqlClient: SQLClient

    override suspend fun start() {
        val router = Router.router(vertx)
        Constants.init(vertx, router)
        // 设置null的字段不输出
        DatabindCodec.mapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 协程太爽
        val port = config.getInteger("http.port", 8000)
        vertx.createHttpServer().requestHandler(router).listenAwait(port)

        val config = readFile("db-config.json", vertx)
        sqlClient = JDBCClient.createShared(vertx, JsonObject(config))

        vertx.deployVerticleAwait(UserVerticle(router))
        vertx.deployVerticleAwait(GlobalVerticle(router))
        vertx.deployVerticleAwait(MockVerticle(router))
        vertx.deployVerticleAwait(RedisVerticle())
        vertx.deployVerticleAwait(LoginVerticle(router, sqlClient))
    }
}
