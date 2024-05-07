package com.ci.hub.controller.impl

import com.ci.hub.common.Result
import com.ci.hub.controller.ICompanyController
import com.ci.hub.entity.Company
import com.ci.hub.server.impl.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
@RestController
@RequestMapping("company")
class CompanyController : ICompanyController {

    @Autowired
    lateinit var companyService: CompanyService
    /**
     * 实现获取公司列表的函数。
     * @return 公司列表的Result包装对象。
     */
    @GetMapping
    override fun getCompanyLst(): Result<List<Company>> {
        // 实际实现可能涉及数据库查询或其他数据源调用
        val companyLst = companyService.getCompanyLst()
        return Result.Success(companyLst) // 假设成功返回空列表
    }

    /**
     * 根据行业ID获取公司的函数。
     * @param industryId 要查询的行业ID。
     * @return 包含指定行业公司列表的Result对象。
     */
    @GetMapping("{industryId}")
    override fun getCompanyByIndustryId(@PathVariable industryId: Long): Result<List<Company>> {
        // 实际实现应包含根据industryId筛选的逻辑
        return Result.Success(listOf()) // 假设成功返回空列表
    }

    /**
     * 创建公司的函数。
     * @param company 要创建的公司对象。
     * @return 包含新创建公司信息的Result对象。
     */
    @PostMapping
    override fun createCompany(@RequestBody company: Company): Result<Company> {
        // 实际实现应处理公司创建的逻辑，包括数据验证和存储
        return Result.Success(company) // 假设成功直接返回传入的公司对象
    }

    /**
     * 更新公司信息的函数。
     * @param company 包含更新信息的公司对象。
     * @return 更新操作结果的Result对象。
     */
    @PutMapping
    override fun updateCompany(@RequestBody company: Company): Result<Company> {
        // 实际实现应包括查找并更新现有公司记录的逻辑
        return Result.Success(company) // 假设成功直接返回传入的公司对象
    }

    /**
     * 下架公司的函数。
     * @param companyId 要删除的公司ID。
     * @return 表示删除操作是否成功的Result<Boolean>对象。
     */
    @DeleteMapping("{companyId}")
    override fun deleteCompany(@PathVariable companyId: Long): Result<Boolean> {
        // 实际实现应执行删除公司记录的操作，并返回操作状态
        return Result.Success(true) // 假设成功删除返回true
    }
}