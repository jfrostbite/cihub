package com.ci.hub.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.ci.hub.common.GroupType
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

@TableName("user") // 指定表名
data class User(
    @TableId(value = "id", type = IdType.AUTO) // 标识主键字段和主键生成策略
    val id: Long? = null,

    @TableField("uname")
    val uname: String,

    @TableField("age")
    val age: Int = 0,

    @TableField("phone")
    val phone: String = "",

    @TableField("addr")
    val addr: String = "",

    @TableField("vip")
    val vip: Boolean = false,

    @TableField("ctime")
    val ctime: LocalDateTime = LocalDateTime.now(),

    @TableField("weid")
    val weid: String = "",

    @TableField("openid")
    val openid: String = "",

    @TableField("crop")
    val crop: String = "",

    @TableField("upwd")
    @JsonIgnore
    val upwd: String = "",

    @TableField("groupid")
    val groupid: Int = GroupType.DEFAULT,
)
