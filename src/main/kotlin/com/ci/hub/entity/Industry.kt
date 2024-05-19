package com.ci.hub.entity

import com.mybatisflex.annotation.*

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/14
 */
@Table(value = "industry")
data class Industry(
    @Id(keyType = KeyType.Auto)
    val id: Long? = null,
    @ColumnAlias("industry_name")
    val name: String = "",
    @Column(ignore = true)
    var icon: String = ""
)