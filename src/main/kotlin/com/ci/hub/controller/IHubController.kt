package com.ci.hub.controller

import Industry
import com.ci.hub.common.Result
import com.ci.hub.entity.Company
import com.ci.hub.entity.User
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 这里是对该类的简要描述，可以包括主要功能、用途等信息
 *
 * @author Kevin
 * @version 1.0
 * @since 2024/05/06
 */
@RequestMapping("api")
interface IHubController

interface IUserController: IHubController {

    //微信首次登录创建的用户
    fun createUser(user: User): Result<String>

    //管理员登录
    fun adminLogin(username: String, password: String): Result<String>

    //用户登录
    fun userLogin(openId: String, sessionKey: String): Result<String>

    //获取用户信息
    fun getUserInfo(userId: Long): Result<User>

    //获取用户列表
    fun getUserList(): Result<List<User>>

}

interface ICompanyController: IHubController {
    // 获取公司列表的函数
    fun getCompanyLst(): Result<List<Company>>

    // 根据行业ID获取公司的函数
    fun getCompanyByIndustryId(industryId: Long): Result<List<Company>>

    // 创建公司的函数
    fun createCompany(company: Company): Result<Company>

    // 更新公司信息的函数
    fun updateCompany(company: Company): Result<Company>

    // 删除公司的函数
    fun deleteCompany(companyId: Long): Result<Boolean>
}

interface IIndustryController: IHubController {
    // 获取行业列表的函数
    fun getIndustryLst(): Result<List<Industry>>
    // 创建行业的函数
    fun createIndustry(industry: Industry): Result<Industry>
    // 更新行业的函数
    fun updateIndustry(industry: Industry): Result<Industry>
    // 删除行业的函数
    fun deleteIndustry(industryId: Long): Result<Boolean>
    // 获取行业详情的函数
    fun getIndustryDetail(industryId: Long): Result<Industry>
}