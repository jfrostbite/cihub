package com.ci.hub.entity


import com.ci.hub.common.GroupType
import com.fasterxml.jackson.annotation.JsonProperty
import com.mybatisflex.annotation.Column
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import java.time.LocalDateTime

@Table("user") // 指定表名
data class User(
    @Id(keyType = KeyType.Auto) // 标识主键字段和主键生成策略
    val id: Long? = null,

    @Column("uname")
    val username: String = "User_${System.currentTimeMillis()}",

    @Column("age")
    val age: Int = 0,

    @Column("phone")
    val phone: String = "",

    @Column("addr")
    val addr: String = "",

    @Column("vip")
    val vip: Boolean = false,

    @Column("ctime")
    val ctime: LocalDateTime = LocalDateTime.now(),

    @Column("weid")
    val weid: String = "",

    @Column("openid")
    val openid: String = "",

    @Column("crop")
    val crop: String = "",

    @Column("upwd")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val upwd: String = "",

    @Column("groupid")
    val groupid: Int = GroupType.DEFAULT,

    @Column("nick")
    val nickname: String = "",

    var token: String = "",

    val avatar: String = "",
)
