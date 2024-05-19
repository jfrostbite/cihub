package com.ci.hub.server


import com.ci.hub.entity.*
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.service.IService
import org.springframework.web.multipart.MultipartFile
import java.io.File


/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
interface ICompanyService : IService<Company> {

    //创建公司数据
    fun createCompany(company: Company): Boolean

    //更新公司数据
    fun updateCompany(company: Company): Boolean

    //删除公司数据
    fun deleteCompany(companyId: Long): Boolean
    fun getCompanyByName(name: String): Company?

    //获取公司列表
    fun getCompanyLst(industryId: Long? = null): List<Company>

    //根据industryId获取公司列表
    fun getCompanyByIndustryId(industryId: Long?): List<Company>

    fun getCompanyPage(pageNum: Int, pageSize: Int, industryId: Long? = null): Page<Company>
}

interface IUserService {
    fun createUser(user: User): Boolean
    fun createGroup(group: UserGroup): Boolean
    fun updateUser(user: User): Boolean
    fun deleteUser(userId: Long): Boolean
    fun getUserById(id: Long): User?
    fun getUserByOpenid(openid: String): User?
    fun getAllUser(): List<User>
    fun getUser(uname: String): User?
}

interface IndustryService {
    fun getIndustryLst(): List<Industry>
    fun getIndustryPage(pageNum: Int, pageSize: Int): Page<Industry>
    fun createIndustry(industry: Industry): Boolean
    fun createIndustry(industrys: List<Industry>): Boolean
    fun updateIndustry(industry: Industry): Boolean
    fun deleteIndustry(industryId: Long): Boolean
    fun getIndustryById(industry: List<Industry>): Boolean
}

interface IAuthService {
    fun login(username: String, password: String): User
    fun wxLogin(openId: String, sessionKey: String): User
}

interface InComService {
    fun createInCom(inComList: List<InCom>): Boolean
    fun createInCom(inCom: InCom): Boolean
    fun updateInCom(inCom: InCom): Boolean
    fun deleteInCom(inComId: Long): Boolean
}

interface IFileService {

    suspend fun saveFileAsync(multipartFile: MultipartFile): File
    fun saveFile(multipartFile: MultipartFile, exec: (t: MediaFile) -> Unit = {}): MediaFile
    fun saveFileById(
        userId: Long? = null,
        companyId: Long? = null,
        industryId: Long? = null,
        isLogo: Boolean = false,
        multipartFile: MultipartFile
    ): MediaFile

}

interface IMediaFileService {
    fun createMedia(mediaFile: MediaFile): Boolean
    fun updateMedia(mediaFile: MediaFile): Boolean
    fun deleteMedia(mediaId: Long): Boolean
    fun getMediaLst(): List<MediaFile>
    fun getMediaByUserIcon(userId: Long): MediaFile
    fun getMediaByUserId(userId: Long): List<MediaFile>
    fun getMediaByIndustryId(industryId: Long): List<MediaFile>
    fun getMediaByCompanyId(companyId: Long): List<MediaFile>
}




