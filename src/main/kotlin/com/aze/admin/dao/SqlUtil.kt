package com.aze.admin.dao

import com.aze.admin.util.isEmpty
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.sql.SQLClient
import io.vertx.ext.sql.SQLOptions
import io.vertx.kotlin.core.json.get
import io.vertx.kotlin.ext.sql.*


/**
 * 和数据库的相关操作
 */
object SqlUtil {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * 返回一条的结果
     * @return 为空返回空对象
     */
    suspend fun getWithParam(sqlClient: SQLClient, sql: String, array: JsonArray = JsonArray()): JsonObject {
        val listWithParam = listWithParam(sqlClient, sql, array)
        if (isEmpty(listWithParam)) {
            return JsonObject()
        }
        return listWithParam[0]
    }


    /**
     * 获取list
     */
    suspend fun listWithParam(sqlClient: SQLClient, sql: String, array: JsonArray): List<JsonObject> {
        val connection = sqlClient.getConnectionAwait()
        val resultSet = if (array.isEmpty) {
            connection.queryAwait(sql)
        } else {
            connection.queryWithParamsAwait(sql, array)
        }
        connection.close()
        if (resultSet.numRows < 1) {
            return emptyList()
        }
        return resultSet.rows
    }

    suspend fun <T> insert(sqlClient: SQLClient, sql: String, array: JsonArray): T {
        val connection = sqlClient.getConnectionAwait()
        connection.setOptions(SQLOptions().setAutoGeneratedKeys(true))
        val result = if (array.isEmpty) {
            connection.updateAwait(sql)
        } else {
            connection.updateWithParamsAwait(sql, array)
        }
        connection.close()
        return result.keys[0]
    }

}