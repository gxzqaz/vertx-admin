package com.aze.admin.verticle

import com.aze.admin.dao.SqlUtil
import com.aze.admin.module.JWTOptionExtend
import com.aze.admin.module.JWT_AUTH
import com.aze.admin.util.responseJson
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.sql.SQLClient
import io.vertx.ext.web.Router
import io.vertx.kotlin.core.json.jsonArrayOf
import io.vertx.kotlin.coroutines.CoroutineVerticle
import kotlinx.coroutines.launch
import java.time.Instant

class LoginVerticle(
    private val router: Router,
    private val sqlClient: SQLClient
) : CoroutineVerticle() {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun start() {
        SqlUtil.insert<Long>(
            sqlClient,
            "insert into bd_user(username, pwd, create_time) values (?,?,?)",
            jsonArrayOf(
                "admin",
                "123",
                Instant.now().toEpochMilli()
            )
        )

        // 对排除的login接口进行适配, 以后需要从数据库读取数据
        router.post("/api/login").handler { ctx ->
            val userInfo = ctx.bodyAsJson
            val username = userInfo.getString("username")
            val password = userInfo.getString("password")
            // 登陆类型,现在只有一种
            val type = userInfo.getString("type")

            val token =
                JWT_AUTH.generateToken(JsonObject(), JWTOptionExtend().setExpiresInSeconds(3600).addPermission(""))
            launch {
                //                val requestAwait = vertx.eventBus().requestAwait<String>(LOCAL_REDIS_GET, "")
//                responseJson(
//                    ctx, AjaxResult.ok(requestAwait)
//                )
                val res = SqlUtil.getWithParam(sqlClient, "select * from bd_user")
                responseJson(ctx, res)
            }
        }
    }
}
