package com.ci.hub.controller.impl

import Industry
import com.ci.hub.common.Result
import com.ci.hub.controller.IIndustryController
import com.ci.hub.server.IIndustryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
@RestController
@RequestMapping("industry")
class IndustryController: IIndustryController {

    @Autowired
    lateinit var service: IIndustryService

    @GetMapping
    override fun getIndustryLst(): Result<List<Industry>> {
        val list = service.getIndustryLst()
        return Result.Success(list)
    }

    override fun createIndustry(industry: Industry): Result<Industry> {
        TODO("Not yet implemented")
    }

    override fun updateIndustry(industry: Industry): Result<Industry> {
        TODO("Not yet implemented")
    }

    override fun deleteIndustry(industryId: Long): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getIndustryDetail(industryId: Long): Result<Industry> {
        TODO("Not yet implemented")
    }
}