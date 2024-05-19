package com.ci.hub.entity


import cn.hutool.core.date.DateUtil
import com.mybatisflex.annotation.Column
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import java.time.LocalDateTime
import java.util.*

@Table("company")
data class Company(
    @Id(keyType = KeyType.Auto)
    val id: Long? = null,
    val name: String = "",
    var phone: String? = null,
    var address: String? = null,
    var city: String? = null,
    var legalRep: String? = null,
    var founded: Date? = null,
    var overview: String? = null,
    var website: String? = null,
    var scope: String? = null,
    var video: String? = null,
    var pics: String? = null,
    var description: String? = null,
    var email: String? = null,
    @Column(isLogicDelete = true)
    var deleted: Boolean = false,
    var enabled: Boolean = true,
    val created: LocalDateTime? = DateUtil.toLocalDateTime(Date()),
    @Column("created_by")
    val createdBy: String? = null,
    var industry: List<Industry>? = null,
    var path: List<MediaFile>? = null,
    @Column(ignore = true)
    var logo: String? = null,
) {

}
