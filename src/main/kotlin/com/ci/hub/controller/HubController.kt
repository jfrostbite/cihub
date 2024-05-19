package com.ci.hub.controller


import com.ci.hub.common.Result
import com.ci.hub.entity.Company
import com.ci.hub.entity.Industry
import com.ci.hub.entity.User
import com.mybatisflex.core.paginate.Page
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.multipart.MultipartFile

/**
 * 这里是对该类的简要描述，可以包括主要功能、用途等信息
 *
 * @author Kevin
 * @version 1.0
 * @since 2024/05/06
 */
@RequestMapping("api")
interface IHubController

interface IUserController : IHubController {

    //创建的用户
    fun createUser(user: User): Result<String>

    //微信首次登录创建的用户
    fun createUserByOpenId(openId: String, phone: String?, weid: String): Result<User>

    //管理员登录
    fun adminLogin(user: User): Result<User>

    //用户登录
    fun userLogin(openId: String, sessionKey: String): Result<User>

    //获取用户信息
    fun getUserInfo(userId: Long): Result<User>

    //获取用户列表
    fun getUserList(): Result<List<User>>

    fun getRouter(): String

}

interface ICompanyController : IHubController {
    // 获取公司列表的函数
    fun getCompanyLst(): Result<List<Company>>

    // 根据行业ID获取公司的函数
    fun getCompanyByIndustryId(industryId: Long?): Result<List<Company>>

    fun getCompanyPage(pageIndex: Int, pageSize: Int): Result<Page<Company>>

    fun getCompanyPageByIndustryId(pageIndex: Int, pageSize: Int,industryId: Long?): Result<Page<Company>>

    // 创建公司的函数
    fun createCompany(company: Company): Result<Company>

    fun createCompanyWithMedia(info: String, logo: MultipartFile?, vararg file: MultipartFile): Result<Company>

    //根据名称获取公司
    fun getCompanyByName(name: String): Result<Company>

    // 更新公司信息的函数
    fun updateCompany(company: Company): Result<Company>

    // 删除公司的函数
    fun deleteCompany(companyId: Long): Result<Boolean>
}

interface IIndustryController : IHubController {
    // 获取行业列表的函数
    fun getIndustryLst(): Result<List<Industry>>

    // 获取行业分页的函数
    fun getIndustryPage(pageIndex: Int, pageSize: Int): Result<Page<Industry>>

    // 创建行业的函数
    fun createIndustry(info: String, file: MultipartFile?): Result<Industry>

    // 更新行业的函数
    fun updateIndustry(industry: Industry): Result<Industry>

    // 删除行业的函数
    fun deleteIndustry(industryId: Long): Result<Boolean>

}

interface IFileController {

    var host: String
    var port: String
    var path: String

    suspend fun uploadFileAsync(vararg files: MultipartFile): Result<List<String>>
    fun uploadFile(vararg file: MultipartFile): Result<List<String>>


    fun downloadFile(fileName: String, response: HttpServletResponse)
}