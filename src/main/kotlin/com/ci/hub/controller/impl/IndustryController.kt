package com.ci.hub.controller.impl


import com.alibaba.fastjson2.to
import com.ci.hub.common.Result
import com.ci.hub.controller.IIndustryController
import com.ci.hub.entity.Industry
import com.ci.hub.server.IFileService
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
@RequestMapping("industry")
class IndustryController : IIndustryController {

    @Autowired
    lateinit var mediaFileService: IFileService

    @Autowired
    lateinit var service: IndustryService

    @GetMapping
    override fun getIndustryLst(): Result<List<Industry>> {
        val list = service.getIndustryLst()
        return Result.Success(list)
    }

    @GetMapping("page")
    override fun getIndustryPage(@RequestParam pageIndex: Int, @RequestParam pageSize: Int): Result<Page<Industry>> {
        return Result.Success(service.getIndustryPage(pageIndex, pageSize))
    }

    @PostMapping
    @Transactional
    override fun createIndustry(
        @RequestParam(name = "info") info: String,
        @RequestParam(required = false) file: MultipartFile?
    ): Result<Industry> {
        info.to<Industry>()?.let { industry ->
            val createIndustry = service.createIndustry(industry)
            if (!createIndustry) {
                return Result.Error("创建失败")
            }
            file?.let {
                mediaFileService.saveFileById(industryId = industry.id, isLogo = true, multipartFile = it).let {
                    industry.icon = it.path
                }
            }
            return Result.Success(industry)
        }
        return Result.Error("创建失败")
    }

    @PutMapping
    override fun updateIndustry(@RequestBody industry: Industry): Result<Industry> {
        val updateIndustry = service.updateIndustry(industry)
        if (!updateIndustry) {
            return Result.Error("更新失败")
        }
        return Result.Success(industry)
    }

    @DeleteMapping
    override fun deleteIndustry(industryId: Long): Result<Boolean> {
        val deleteIndustry = service.deleteIndustry(industryId)
        if (!deleteIndustry) {
            return Result.Error("删除失败")
        }
        return Result.Success(true)
    }
}