package com.ci.hub.server.impl

import com.ci.hub.entity.Company
import com.ci.hub.entity.InCom
import com.ci.hub.entity.Industry
import com.ci.hub.entity.MediaFile
import com.ci.hub.exception.ServiceException
import com.ci.hub.mapper.CompanyMapper
import com.ci.hub.server.ICompanyService
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryMethods.select
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.kotlin.extensions.db.queryOne
import com.mybatisflex.kotlin.extensions.kproperty.column
import com.mybatisflex.kotlin.extensions.kproperty.defaultColumns
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service


/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
@Service
class CompanyService : ServiceImpl<CompanyMapper, Company>(), ICompanyService {

    override fun createCompany(company: Company): Boolean {
        try {
            return saveOrUpdate(company)
        } catch (e: Exception) {
            throw ServiceException(e.message ?: "创建公司失败")
        }
    }

    /**
     * 这里用leftjoin 关联 行业表、中间表、媒体文件表，3次leftjoin，数据重复，利用distinct 去重了Industry的行业数据，
     * 但是加入media的leftjoin，数据有重复了，出现了 industry 跟 media 重复的情况，所以这里加入了
     * distinct(MediaFile::class.allColumns),不起作用，导致 industry media的数据都重复，所以在Media中的进行了 equals的
     * 复写，然后在company中利用set 进行数据的去重。暂时这样，没有从sql 中去除重复数据。
     *
     */
    override fun getCompanyLst(industryId: Long?): List<Company> = list(
        QueryWrapper.create().select(
            Company::class.defaultColumns,
            Industry::class.defaultColumns,
            MediaFile::class.defaultColumns
        )
            .select("m_logo.path as logo")
            .from(Company::class.java).`as`("c")
            .leftJoin<QueryWrapper>(InCom::class.java).`as`("ic").on(Company::id.column.eq(InCom::comId.column))
            .leftJoin<QueryWrapper>(Industry::class.java).`as`("i").on(InCom::indId.column.eq(Industry::id.column))
            .leftJoin<QueryWrapper>(MediaFile::class.java)
            .on(Company::id.column.eq(MediaFile::companyId.column).and(MediaFile::isLogo.column.ne(true)))
            .leftJoin<QueryWrapper>(
                select(MediaFile::path.column, MediaFile::companyId.column)
                    .from(MediaFile::class.java)
                    .where(MediaFile::isLogo.column.eq(true))
            ).`as`("m_logo")
            .on("c.id = m_logo.company_id")
            .apply {
                industryId?.let {
                    where(Industry::id.column.eq(industryId))
                }
            }
    )

    override fun getCompanyByIndustryId(industryId: Long?): List<Company> = getCompanyLst(industryId)

    override fun getCompanyPage(pageNum: Int, pageSize: Int, industryId: Long?): Page<Company> {
        PageHelper.startPage<Company>(pageNum, pageSize)
        val companyLst = getCompanyLst(industryId)
        val page: PageInfo<Company> = PageInfo<Company>(companyLst)
        return Page(companyLst, pageNum, pageSize, page.total)
    }

    override fun updateCompany(company: Company): Boolean {
        return updateById(company)
    }

    override fun deleteCompany(companyId: Long): Boolean = removeById(companyId)

    override fun getCompanyByName(name: String): Company? {
        return queryOne<Company>(Company::name.column) {
            where(Company::name.column.eq(name))
        }
    }
}