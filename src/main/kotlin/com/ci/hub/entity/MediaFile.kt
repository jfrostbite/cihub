package com.ci.hub.entity

import com.mybatisflex.annotation.*
import java.time.LocalDateTime

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/17
 */
@Table("media")
data class MediaFile(
    @Id(keyType = KeyType.Auto)
    var id: Long? = null,

    @Column
    @ColumnAlias("media_name")
    var name: String = "",

    @Column
    var ext: String? = null,

    @Column
    var type: String? = null,

    @Column
    var path: String = "",

    var isLogo: Boolean = false,

    var deleted: Boolean = false,

    @Column
    var companyId: Long? = null,

    @Column
    var userId: Long? = null,

    @Column
    var industryId: Long? = null,

    val time: LocalDateTime = LocalDateTime.now(),

    ){

    //重写hashcode 和 equals方法
    override fun hashCode(): Int {
        return path.hashCode() ?: 0
    }
    override fun equals(other: Any?): Boolean {
        if (other is MediaFile) {
            return path == other.path
        }
        return false
    }
}