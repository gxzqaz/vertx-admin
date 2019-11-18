package com.aze.admin.data

import java.sql.Timestamp

data class User constructor(
    var username: String? = null,
    var pwd: String? = null,
    var createTime: Long? = null,
    var modifyTime: Long? = null
)
