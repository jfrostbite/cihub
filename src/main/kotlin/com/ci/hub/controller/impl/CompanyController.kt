package com.ci.hub.controller.impl

import com.alibaba.fastjson2.to
import com.ci.hub.common.Result
import com.ci.hub.controller.ICompanyController
import com.ci.hub.entity.Company
import com.ci.hub.entity.InCom
import com.ci.hub.exception.ServiceException
import com.ci.hub.server.ICompanyService
import com.ci.hub.server.IFileService
import com.ci.hub.server.InComService
import com.ci.hub.server.IndustryService
import com.mybatisflex.core.paginate.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
@RestController
@RequestMapping("company")
class CompanyController : ICompanyController {

    @Autowired
    lateinit var service: ICompanyService

    @Autowired
    lateinit var industryService: IndustryService

    @Autowired
    lateinit var inComService: InComService

    @Autowired
    lateinit var mediaFileService: IFileService

    /**
     * 实现获取公司列表的函数。
     * @return 公司列表的Result包装对象。
     */
    @Deprecated("废弃")
    override fun getCompanyLst(): Result<List<Company>> {
        // 实际实现可能涉及数据库查询或其他数据源调用
        val companyLst = service.getCompanyLst()
        return Result.Success(companyLst) // 假设成功返回空列表
    }

    /**
     * 根据行业ID获取公司的函数。
     * @param industryId 要查询的行业ID。
     * @return 包含指定行业公司列表的Result对象。
     */
    @GetMapping
    override fun getCompanyByIndustryId(
        @RequestParam(
            required = false
        ) industryId: Long?
    ): Result<List<Company>> {
        return Result.Success(service.getCompanyByIndustryId(industryId)) // 假设成功返回空列表
    }

    @Deprecated(
        "已废弃", ReplaceWith(
            "Result.Success(service.getCompanyPage(pageIndex, pageSize))",
            "com.ci.hub.common.Result"
        )
    )
    override fun getCompanyPage(@RequestParam pageIndex: Int, @RequestParam pageSize: Int): Result<Page<Company>> {
        return Result.Success(service.getCompanyPage(pageIndex, pageSize))
    }

    @GetMapping("page")
    override fun getCompanyPageByIndustryId(
        @RequestParam pageIndex: Int,
        @RequestParam pageSize: Int,
        @RequestParam(required = false) industryId: Long?,
    ): Result<Page<Company>> {
        return Result.Success(service.getCompanyPage(pageIndex, pageSize, industryId))
    }

    /**
     * 创建公司的函数。
     * @param company 要创建的公司对象。
     * @return 包含新创建公司信息的Result对象。
     */

    @PostMapping
    @Transactional
    override fun createCompany(@RequestBody company: Company): Result<Company> {
        if (company.name.isEmpty()) {
            return Result.Error("公司名称不能为空")
        }
        service.getCompanyByName(company.name)?.let { return Result.Error("公司已存在") }
        company.industry?.let { industry ->
            try {
                val result = service.createCompany(company)
//                val result2 = industryService.createIndustry(industry)
                val inComs = industry.map { InCom(it.id, company.id) }
                val result3 = inComService.createInCom(inComs)
                if (result && result3) {
                    return Result.Success(company) // 假设成功返回传入的公司对象
                } else {
                    return Result.Error("创建公司失败") // 假设失败抛出异常
                }
            } catch (e: Exception) {
                throw ServiceException("创建公司失败")
            }
        }
        return Result.Error("请选择所属行业")
    }

    @PostMapping("media")
    @Transactional
    override fun createCompanyWithMedia(
        @RequestParam info: String,
        @RequestParam(required = false) logo: MultipartFile?,
        @RequestParam(required = false) vararg file: MultipartFile
    ): Result<Company> {
        val company = info.to<Company>()
        if (company?.name.isNullOrEmpty()) {
            return Result.Error("公司名称不能为空")
        }
        service.getCompanyByName(company.name)?.let { return Result.Error("公司已存在") }
        company.industry?.let { industry ->
            val result = service.createCompany(company).apply {
                if (this) {
                    file.map { file ->
                        mediaFileService.saveFileById(companyId = company.id, multipartFile = file)
                    }.let {
                        company.path = it
                    }
                    logo?.let {
                        mediaFileService.saveFileById(companyId = company.id, isLogo = true, multipartFile = it)
                    }.let {
                        company.logo = it?.path
                    }
                }
            }
            //TODO 这里本来实现创建行业，前端有问题，取消了
//            val result2 = industryService.createIndustry(industry)
            val inComs = industry.map { InCom(it.id, company.id) }
            val result3 = inComService.createInCom(inComs)
            if (result && result3) {
                return Result.Success(company) // 假设成功返回传入的公司对象
            } else {
                return Result.Error("创建公司失败") // 假设失败抛出异常
            }
        }
        return Result.Error("请选择所属行业")
    }

    override fun getCompanyByName(name: String): Result<Company> {
        return Result.Success(service.getCompanyByName(name))
    }

    /**
     * 更新公司信息的函数。
     * @param company 包含更新信息的公司对象。
     * @return 更新操作结果的Result对象。
     */
    @PutMapping
    override fun updateCompany(@RequestBody company: Company): Result<Company> {
        if (company.name.isEmpty()) {
            return Result.Error("公司名称不能为空")
        }
        val updateCompany = service.updateCompany(company)
        if (updateCompany) {
            return Result.Success(company) // 假设成功直接返回传入的公司对象
        }
        return Result.Error("更新公司失败") // 假设失败返回错误信息
    }

    /**
     * 下架公司的函数。
     * @param companyId 要删除的公司ID。
     * @return 表示删除操作是否成功的Result<Boolean>对象。
     */
    @DeleteMapping
    override fun deleteCompany(@RequestParam(name = "id") companyId: Long): Result<Boolean> {
        val deleteCompany = service.deleteCompany(companyId)
        if (!deleteCompany) {
            return Result.Error("已删除") // 假设失败返回错误信息
        }
        return Result.Success(true) // 假设成功删除返回true
    }
}