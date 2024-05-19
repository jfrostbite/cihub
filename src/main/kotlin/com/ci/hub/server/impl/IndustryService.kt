package com.ci.hub.server.impl

import com.ci.hub.entity.Industry
import com.ci.hub.entity.MediaFile
import com.ci.hub.exception.ServiceException
import com.ci.hub.mapper.IndustryMapper
import com.ci.hub.server.IndustryService
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.kotlin.extensions.db.paginate
import com.mybatisflex.kotlin.extensions.kproperty.allColumns
import com.mybatisflex.kotlin.extensions.kproperty.column
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
@Service
class IndustryService : ServiceImpl<IndustryMapper, Industry>(), IndustryService {

    override fun getIndustryLst(): List<Industry> {
        return list()
    }

    override fun getIndustryPage(pageNum: Int, pageSize: Int): Page<Industry> =
        paginate(pageNum, pageSize) {
            select(
                Industry::class.allColumns,
                MediaFile::path.column.`as`("icon")
            )
            from(Industry::class.java)
            leftJoin(MediaFile::class.java).on(Industry::id.column.eq(MediaFile::industryId.column))
        }

    override fun createIndustry(industrys: List<Industry>): Boolean {

        try {
            return saveBatch(industrys)
        } catch (e: Exception) {
            throw ServiceException(e.message ?: "创建行业失败")
        }
    }

    override fun createIndustry(industry: Industry): Boolean {
        return saveOrUpdate(industry)
    }

    override fun updateIndustry(industry: Industry): Boolean {
        return updateById(industry)
    }

    override fun deleteIndustry(industryId: Long): Boolean {
        return removeById(industryId)
    }

    override fun getIndustryById(industry: List<Industry>): Boolean {
        TODO("Not yet implemented")
    }
}