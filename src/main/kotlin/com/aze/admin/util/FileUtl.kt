package com.aze.admin.util

import com.aze.admin.common.exception.BizException
import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.kotlin.core.file.readFileAwait

suspend fun readFile(path: String, vertx: Vertx): Buffer {
    if (isEmpty(path)) throw BizException("path 不能为空")
    if (path.startsWith("/")) throw BizException("path不能以斜杠开头, 会报错")
    return vertx.fileSystem().readFileAwait(path)
}
