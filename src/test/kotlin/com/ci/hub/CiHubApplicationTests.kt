package com.ci.hub


import com.ci.hub.controller.IFileController
import com.ci.hub.controller.impl.CompanyController
import com.ci.hub.entity.Company
import com.ci.hub.entity.InCom
import com.ci.hub.entity.Industry
import com.ci.hub.entity.MediaFile
import com.ci.hub.mapper.CompanyMapper
import com.ci.hub.server.InComService
import com.ci.hub.server.IndustryService
import com.ci.hub.server.impl.CompanyService
import com.ci.hub.server.impl.MediaFileService
import com.mybatisflex.core.paginate.Page
import com.mybatisflex.core.query.QueryMethods.select
import com.mybatisflex.core.query.QueryWrapper
import com.mybatisflex.kotlin.extensions.kproperty.column
import com.mybatisflex.kotlin.extensions.kproperty.defaultColumns
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.system.ApplicationHome
import org.springframework.boot.test.context.SpringBootTest
import java.io.File


@SpringBootTest
class CiHubApplicationTests {

    @Autowired
    private lateinit var c: CompanyController

    @Autowired
    lateinit var mapper: CompanyMapper

    @Autowired
    lateinit var incomeService: InComService

    @Autowired
    lateinit var companyService: CompanyService

    @Autowired
    lateinit var mediaFileService: MediaFileService

    @Autowired
    lateinit var fileController: IFileController

    @Autowired
    lateinit var industryService: IndustryService

    @Test
    fun contextLoads(): Unit = runBlocking {

        val page = companyService.page(Page.of(1, 10),
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
                .on("c.id = m_logo.company_id"))
        page.records.forEach(::println)
        page.records.size.let(::println)


        /*       val list = mapper.selectListByQuery(
                   QueryWrapper.create()
                       .select(
                           Company::class.defaultColumns,
                           Industry::class.defaultColumns,
                           MediaFile::class.defaultColumns
                       )
                       .from(Company::class.java)
                       .leftJoin<QueryWrapper>(InCom::class.java).`as`("ic").on(Company::id.column.eq(InCom::comId.column))
                       .leftJoin<QueryWrapper>(Industry::class.java).`as`("i").on(InCom::indId.column.eq(Industry::id.column))
                       .leftJoin<QueryWrapper>(MediaFile::class.java).`as`("m")
                       .on(Company::id.column.eq(MediaFile::companyId.column))
               )*/

//        list.forEach(::println)

//        testHome()

//        incomeService.createInCom(InCom(ind_id = 1, com_id = 1))

//        var a = Company(name = "test93").apply {
//            industry = listOf(Industry(
//                14,
//                name = "test 5industry",
//                icon = "test icon"
//            ))
//        }
//        println(c.createCompany(a).toString())

        //kpt
        /*val qw = QueryWrapper.create()
            .select(CompanyTableDef.COMPANY.ALL_COLUMNS)
            .select(IndustryTableDef.INDUSTRY.NAME)
            .select(IndustryTableDef.INDUSTRY.ICON)
            .from(CompanyTableDef.COMPANY)
            .leftJoin<QueryWrapper>(InComTableDef.IN_COM)
            .on(Company::id.column.eq(InCom::comId.column))
            .leftJoin<QueryWrapper>(IndustryTableDef.INDUSTRY)
            .on(InCom::indId.column.eq(Industry::id.column))

        val list = mapper.selectListByQuery(qw)
        list.forEach {
            println(it.toString())
            println(it.industry.toString())
        }*/


        /*

                val company = query<Company> {
                    leftJoin(InCom::class.java)
                        .on(Company::id.eq(InCom::com_id))
                        .leftJoin(Industry::class.java)
                        .on(InCom::ind_id.eq(Industry::id))
                        .where(Industry::id.eq(13))
                }
        */

        /* val list = QueryChain.of(mapper)
             .select(
                 CompanyTableDef.COMPANY.ALL_COLUMNS,
                 IndustryTableDef.INDUSTRY.ALL_COLUMNS,
                 IndustryTableDef.INDUSTRY.NAME.`as`("industry_name")
             )

             .from(CompanyTableDef.COMPANY)
             .leftJoin(InComTableDef.IN_COM)
             .on(InComTableDef.IN_COM.COM_ID.eq(CompanyTableDef.COMPANY.ID))
             .leftJoin(IndustryTableDef.INDUSTRY)
             .on(InComTableDef.IN_COM.IND_ID.eq(IndustryTableDef.INDUSTRY.ID))
             .where(IndustryTableDef.INDUSTRY.ID.eq(1))
             .list()
         list.forEach(::println)
         println(list[0].industry.toString())*/


    }

    private fun testHome() {
        val applicationHome = ApplicationHome(fileController.javaClass)
        val absoluteFile = applicationHome.dir.absoluteFile
        val dir = File(absoluteFile, "upload")

        println(dir.absolutePath)
        println(fileController.port)
    }

}