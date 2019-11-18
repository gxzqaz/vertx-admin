package com.aze.admin.common.exception

class SysException(message: String?, error: Throwable?) : RuntimeException(message, error) {

  constructor(message: String?) : this(message, null)

}


class BizException(message: String?, error: Throwable?) : RuntimeException(message, error) {

  constructor(message: String?) : this(message, null)

}
