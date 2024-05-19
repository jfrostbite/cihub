package com.ci.hub.vo


import com.ci.hub.entity.Industry
import com.ci.hub.entity.MediaFile
import java.util.*

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/14
 */
class CompanyVO {
    var id: Long? = null
    var name: String = ""
    var logo: String? = null
    var phone: String? = null
    var addr: String? = null
    var city: String? = null
    var legalrep: String? = null
    var founded: Date? = null
    var overview: String? = null
    var web: String? = null
    var scope: String? = null
    var video: String? = null
    var pics: String? = null
    var description: String? = null
    var email: String? = null

    var userId: Int? = null

    var industryId: Int? = null

    var deleted: Boolean = false
    var enabled: Boolean = true
    var created: Date? = null

    var createdBy: String? = null


    var industry: List<Industry> = arrayListOf()

    var path: List<MediaFile> = arrayListOf()

}
