package com.aze.admin.util

import com.aze.admin.common.constant.APPLICATION_JSON
import com.aze.admin.common.constant.TEXT_HTML
import com.aze.admin.common.constant.TEXT_PLAIN
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpHeaders.CONTENT_TYPE
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext


fun responseJson(routingContext: RoutingContext, any: Any) {
  val response = routingContext.response()
  response.putHeader(CONTENT_TYPE, APPLICATION_JSON).end(JsonUtil.write(any))
}

fun responseJson(routingContext: RoutingContext, buffer: Buffer) {
  val response = routingContext.response()
  response.putHeader(CONTENT_TYPE, APPLICATION_JSON).end(JsonObject(buffer).encode())
}

fun responseHtml(routingContext: RoutingContext) {
  val response = routingContext.response()
  response.putHeader(CONTENT_TYPE, TEXT_HTML).end()
}


fun responseText(routingContext: RoutingContext, text: String) {
  val response = routingContext.response()
  response.putHeader(CONTENT_TYPE, TEXT_PLAIN).end(text)
}
