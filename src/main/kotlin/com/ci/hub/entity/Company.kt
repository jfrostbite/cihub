package com.ci.hub.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.util.Date

@TableName("company")
data class Company(
    @TableId(value = "id", type = IdType.AUTO)
    val id: Long? = null, // 对应于'id'字段，使用@TableId注解标记为主键
    val cname: String, // 对应于'cname'字段
    val logo: String? = null, // 对应于'logo'字段
    val phone: String? = null, // 对应于'phone'字段
    val addr: String? = null, // 对应于'addr'字段
    val city: String? = null, // 对应于'city'字段
    val legalrep: String? = null, // 对应于'legalrep'字段
    @TableField("founded")
    val founded: Date? = null, // 对应于'founded'字段，注意需要导入java.util.Date
    val overview: String? = null, // 对应于'overview'字段
    val web: String? = null, // 对应于'web'字段
    val scope: String? = null, // 对应于'scope'字段
    val video: String? = null, // 对应于'video'字段
    val pics: String? = null, // 对应于'pics'字段
    val brief: String? = null, // 对应于'brief'字段
    val email: String? = null, // 对应于'email'字段
    @TableField("user_id")
    val userId: Int? = null, // 对应于'user_id'字段
    @TableField("industry_id")
    val industryId: Int? = null // 对应于'industry_id'字段
)
