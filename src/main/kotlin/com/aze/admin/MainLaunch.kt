package com.aze.admin

import io.vertx.core.DeploymentOptions
import io.vertx.core.Launcher


fun main(args: Array<String>) {
    // 设置一下日志为log4j2
    System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory")
    // 默认使用四核
    val newArgs = arrayOf("run", "com.aze.admin.MainVerticle", *args)
    MainLaunch().dispatch(newArgs)
}

class MainLaunch : Launcher() {

    override fun beforeDeployingVerticle(deploymentOptions: DeploymentOptions) {
        super.beforeDeployingVerticle(deploymentOptions)
//        deploymentOptions.instances = 2
    }
}
