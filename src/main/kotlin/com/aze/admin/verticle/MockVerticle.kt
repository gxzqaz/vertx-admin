package com.aze.admin.verticle

import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router
import io.vertx.kotlin.coroutines.CoroutineVerticle


class MockVerticle(private val router: Router) : CoroutineVerticle() {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun start() {
        val subRouter = Router.router(vertx)

        // 挂载子路由
        router.mountSubRouter("/mock", subRouter)
    }
}
