package com.ci.hub.server.impl

import com.ci.hub.entity.Company
import com.ci.hub.mapper.CompanyMapper
import com.ci.hub.server.ICompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
@Service
class CompanyService: ICompanyService {

    @Autowired
    lateinit var mapper: CompanyMapper

    override fun getCompanyLst(): List<Company> {
        return mapper.selectList(null)
    }

    override fun getCompanyByIndustryId(industryId: Long): List<Company> {
        TODO("Not yet implemented")
    }

    override fun createCompany(company: Company): Company {
        TODO("Not yet implemented")
    }

    override fun updateCompany(company: Company): Company {
        TODO("Not yet implemented")
    }

    override fun deleteCompany(companyId: Long): Boolean {
        TODO("Not yet implemented")
    }

}