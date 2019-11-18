package com.aze.admin.util

import java.time.format.DateTimeFormatter

private val FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss-SSS")

/**
 * 时间的基本format
 */
val BASE_TIME_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd HH:mm")

val HOUR_MINUTE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
