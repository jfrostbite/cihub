package com.ci.hub

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("com.ci.hub.mapper")
class CiHubApplication

fun main(args: Array<String>) {
    runApplication<CiHubApplication>(*args)
}
