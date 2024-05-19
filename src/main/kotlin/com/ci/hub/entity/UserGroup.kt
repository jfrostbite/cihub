package com.ci.hub.entity

import com.mybatisflex.annotation.Column
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
@Table("user_group")
data class UserGroup(
    @Id(keyType = KeyType.Auto) // 标识主键字段和主键生成策略
    var id: Int? = null,
    @Column("name")
    var name: String,
)