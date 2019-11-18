package com.aze.admin.common.constant

import io.netty.util.AsciiString


@JvmField
val APPLICATION_JSON: AsciiString = AsciiString.cached("application/json")

@JvmField
val APPLICATION_X_WWW_FORM_URLENCODED = AsciiString.cached("application/x-www-form-urlencoded")

@JvmField
val APPLICATION_OCTET_STREAM = AsciiString.cached("application/octet-stream")

@JvmField
val ATTACHMENT = AsciiString.cached("attachment")

@JvmField
val BASE64 = AsciiString.cached("base64")

@JvmField
val BINARY = AsciiString.cached("binary")

@JvmField
val BOUNDARY = AsciiString.cached("boundary")

@JvmField
val BYTES = AsciiString.cached("bytes")

@JvmField
val CHARSET = AsciiString.cached("charset")

@JvmField
val CHUNKED = AsciiString.cached("chunked")

@JvmField
val CLOSE = AsciiString.cached("close")
/**
 * `"compress"`
 */
val COMPRESS = AsciiString.cached("compress")

@JvmField
val CONTINUE = AsciiString.cached("100-continue")

@JvmField
val DEFLATE = AsciiString.cached("deflate")

@JvmField
val X_DEFLATE = AsciiString.cached("x-deflate")

@JvmField
val FILE = AsciiString.cached("file")

@JvmField
val FILENAME = AsciiString.cached("filename")

@JvmField
val FORM_DATA = AsciiString.cached("form-data")

@JvmField
val GZIP = AsciiString.cached("gzip")

@JvmField
val GZIP_DEFLATE = AsciiString.cached("gzip,deflate")
@JvmField
val X_GZIP = AsciiString.cached("x-gzip")

@JvmField
val IDENTITY = AsciiString.cached("identity")

@JvmField
val KEEP_ALIVE = AsciiString.cached("keep-alive")

@JvmField
val MAX_AGE = AsciiString.cached("max-age")

@JvmField
val MAX_STALE = AsciiString.cached("max-stale")

@JvmField
val MIN_FRESH = AsciiString.cached("min-fresh")

@JvmField
val MULTIPART_FORM_DATA = AsciiString.cached("multipart/form-data")

@JvmField
val MULTIPART_MIXED = AsciiString.cached("multipart/mixed")

@JvmField
val MUST_REVALIDATE = AsciiString.cached("must-revalidate")

@JvmField
val NAME = AsciiString.cached("name")

@JvmField
val NO_CACHE = AsciiString.cached("no-cache")

@JvmField
val NO_STORE = AsciiString.cached("no-store")

@JvmField
val NO_TRANSFORM = AsciiString.cached("no-transform")

@JvmField
val NONE = AsciiString.cached("none")

@JvmField
val ZERO = AsciiString.cached("0")

@JvmField
val ONLY_IF_CACHED = AsciiString.cached("only-if-cached")

@JvmField
val PRIVATE = AsciiString.cached("private")

@JvmField
val PROXY_REVALIDATE = AsciiString.cached("proxy-revalidate")

@JvmField
val PUBLIC = AsciiString.cached("public")

@JvmField
val QUOTED_PRINTABLE = AsciiString.cached("quoted-printable")

@JvmField
val S_MAXAGE = AsciiString.cached("s-maxage")

@JvmField
val TEXT_PLAIN: AsciiString = AsciiString.cached("text/plain;charset=UTF-8")

@JvmField
val TEXT_HTML = AsciiString.cached("text/html;charset=UTF-8")

@JvmField
val TRAILERS = AsciiString.cached("trailers")

@JvmField
val UPGRADE = AsciiString.cached("upgrade")

@JvmField
val WEBSOCKET = AsciiString.cached("websocket")
