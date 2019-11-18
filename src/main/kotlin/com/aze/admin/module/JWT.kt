package com.aze.admin.module

import com.aze.admin.common.constant.Constants.vertx
import io.vertx.ext.auth.PubSecKeyOptions
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.auth.jwt.JWTAuthOptions
import io.vertx.ext.jwt.JWTOptions
import io.vertx.ext.web.handler.JWTAuthHandler

/**
 * 过期时间默认为0, 是个时间戳, 到时候还要重写 JWTAuthHandlerImpl 类中的方法去支持
 */
class JWTOptionExtend(var refreshExpire: Long = 0) : JWTOptions() {

}

val JWT_AUTH_OPTIONS: JWTAuthOptions = JWTAuthOptions().addPubSecKey(
    // 下面的publicKey用到的是对称加密, 不能泄露
    PubSecKeyOptions()
        .setAlgorithm("HS256")
        .setPublicKey("A!leE3%#iW3GOwbKfblRtiZlFbkuM^Xd")
        .setSymmetric(true)
)

val JWT_AUTH: JWTAuth = JWTAuth.create(vertx, JWT_AUTH_OPTIONS)

/**
 * 生成handle, 他这个skip不能是list
 */
fun generateHandler(skip: String): JWTAuthHandler = JWTAuthHandler.create(JWT_AUTH, skip)
