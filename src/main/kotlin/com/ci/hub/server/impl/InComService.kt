package com.ci.hub.server.impl

import com.ci.hub.entity.InCom
import com.ci.hub.mapper.InComMapper
import com.ci.hub.server.InComService
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/13
 */
@Service
class InComService: ServiceImpl<InComMapper, InCom>(), InComService {

    override fun createInCom(inComList: List<InCom>): Boolean {
        return saveOrUpdateBatch(inComList)
    }

    override fun createInCom(inCom: InCom): Boolean {
        return save(inCom)
    }

    override fun updateInCom(inCom: InCom): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteInCom(inComId: Long): Boolean {
        TODO("Not yet implemented")
    }

}