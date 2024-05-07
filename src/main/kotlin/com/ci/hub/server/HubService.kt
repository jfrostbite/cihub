package com.ci.hub.server

import Industry
import com.ci.hub.entity.Company
import com.ci.hub.entity.User
import com.ci.hub.entity.UserGroup


/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
interface ICompanyService {

    fun getCompanyLst(): List<Company>

    //根据industryId获取公司列表
    fun getCompanyByIndustryId(industryId: Long): List<Company>

    //创建公司数据
    fun createCompany(company: Company): Company

    //更新公司数据
    fun updateCompany(company: Company): Company

    //删除公司数据
    fun deleteCompany(companyId: Long) : Boolean
}

interface IUserService {
    fun createUser(user: User): Boolean
    fun createGroup(group: UserGroup): Boolean
    fun getUserById(id: Long): User?
    fun getUserByOpenid(openid: String): User?
    fun getAllUser(): List<User>
}

interface IIndustryService {
    fun getIndustryLst(): List<Industry>
}


