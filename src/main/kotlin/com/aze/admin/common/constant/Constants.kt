package com.aze.admin.common.constant

import io.vertx.core.Vertx
import io.vertx.core.eventbus.EventBus
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router

/**
 * 这边只能存放整个应用中唯一的变量, 即使是多实例, vertx也是只有一个
 */
object Constants {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    private var _vertx: Vertx? = null

    private var _eventBus: EventBus? = null

    fun init(vertx: Vertx, router: Router) {
        if (_vertx == null) {
            this._vertx = vertx
        }

        if (_eventBus == null) {
            this._eventBus = vertx.eventBus()
        }
    }

    val vertx by lazy {
        _vertx as Vertx
    }

    val eventBus by lazy {
        _eventBus as EventBus
    }
}
