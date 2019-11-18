package com.aze.admin.verticle

import com.aze.admin.module.generateHandler
import com.aze.admin.util.AjaxResult
import com.aze.admin.util.responseJson
import com.aze.admin.util.responseText
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle


class GlobalVerticle(private val router: Router) : CoroutineVerticle() {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun start() {
        router.route("/static/*").handler(StaticHandler.create())
        router.route().handler(BodyHandler.create())
        // 对api开头的路径进行限制
        router.route("/api/*").handler(generateHandler("/api/login"))
        // 设置首页, 有更好的放到更好的地方
        router.get("/").handler {
            responseText(it, "首页暂时没有数据")
        }
        initErrorInterceptor(router)
    }

    private fun initErrorInterceptor(router: Router) {
        // 设置404页面和拦截页面
        router.route().last().handler { context ->
            logger.warn(context.request().path())
            responseJson(context, AjaxResult.error("404", "页面不存在"))
        }.failureHandler {
            logger.error("error ===> ${it.failure()}")
            val user = it.user();
            // 如果user为空说明未登录过 ,给特殊的状态码
            if (user == null) {
                responseJson(it, AjaxResult.error("403", "请先登录"))
            } else {
                responseJson(it, AjaxResult.error("401", "无权访问接口"))
            }
        }
    }
}
