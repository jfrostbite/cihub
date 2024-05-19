package com.ci.hub.entity

import com.mybatisflex.annotation.Table

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/13
 */
@Table("incom")
data class InCom (
    val indId: Long? = null,
    val comId: Long? = null,
)