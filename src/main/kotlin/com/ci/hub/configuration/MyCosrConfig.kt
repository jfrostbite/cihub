package com.ci.hub.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/12
 */
@Configuration
class MyCosrConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        // 1.创建 CORS 配置对象
        val config = CorsConfiguration()
        // 支持域
        config.addAllowedOriginPattern("*")
        // 是否发送 Cookie
        config.allowCredentials = true
        // 支持请求方式
        config.addAllowedMethod("*")
        // 允许的原始请求头部信息
        config.addAllowedHeader("*")
        // 暴露的头部信息
        config.addExposedHeader("*")
        // 2.添加地址映射
        val corsConfigurationSource = UrlBasedCorsConfigurationSource()
        corsConfigurationSource.registerCorsConfiguration("/**", config)
        // 3.返回 CorsFilter 对象
        return CorsFilter(corsConfigurationSource)
    }

}