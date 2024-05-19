package com.ci.hub.configuration

import com.mybatisflex.core.FlexGlobalConfig
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer
import org.springframework.context.annotation.Configuration

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/19
 */
@Configuration
class MybatisFlexConfig: MyBatisFlexCustomizer {
    override fun customize(globalConfig: FlexGlobalConfig) {
    }
}