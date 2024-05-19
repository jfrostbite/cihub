package com.ci.hub.configuration

import com.ci.hub.interceptor.HttpInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/10
 */

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(httpInterceptor())
            .addPathPatterns("/**")
    }

    @Bean
    fun httpInterceptor(): HttpInterceptor = HttpInterceptor()

}