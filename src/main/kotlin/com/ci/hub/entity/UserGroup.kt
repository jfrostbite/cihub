package com.ci.hub.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
@TableName("user_group")
data class UserGroup(
    @TableId(value = "id", type = IdType.AUTO) // 标识主键字段和主键生成策略
    var id: Int? = null,
    @TableField("name")
    var name: String,
)