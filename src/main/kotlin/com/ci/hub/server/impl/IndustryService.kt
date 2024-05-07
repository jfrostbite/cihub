package com.ci.hub.server.impl

import Industry
import com.ci.hub.mapper.IndustryMapper
import com.ci.hub.server.IIndustryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
@Service
class IndustryService: IIndustryService {

    @Autowired
    lateinit var mapper: IndustryMapper

    override fun getIndustryLst(): List<Industry> {
        return mapper.selectList(null)
    }
}