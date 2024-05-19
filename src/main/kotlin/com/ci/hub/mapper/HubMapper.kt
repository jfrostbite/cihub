package com.ci.hub.mapper


import com.ci.hub.entity.*
import com.mybatisflex.core.BaseMapper

/**
 * UserMapper 接口，继承自 BaseMapper<User>，用于定义与用户相关的数据库操作。
 * 你可以在此接口中添加自定义的SQL方法来满足特定的业务需求。
 */
interface UserMapper : BaseMapper<User> {
    // 例如，可以定义一个根据用户名查询用户的方法
}

/**
 * GroupMapper 接口，继承自 BaseMapper<UserGroup>，用于定义与用户组相关的数据库操作。
 * 此接口为空，但遵循相同的模式，允许根据需要添加自定义方法。
 */
interface GroupMapper: BaseMapper<UserGroup> {
    // 可以在此添加与用户组相关的自定义SQL方法
}


/**
 * CompanyMapper接口，继承自BaseMapper<Company>，用于定义与公司表相关的数据库操作。
 * 这个接口允许你定义一些自定义的查询方法，以满足特定的业务需求。
 *
 * @see BaseMapper 了解BaseMapper的基本用法和提供的默认方法。
 */
interface CompanyMapper : BaseMapper<Company> {
    // 可以在这里添加自定义的查询方法，例如根据特定条件查询公司信息等。
}


/**
 * IndustryMapper接口，继承自BaseMapper<Industry>，用于行业数据的数据库操作。
 * BaseMapper提供了基本的CRUD方法，IndustryMapper可以在此基础上添加自定义的查询方法。
 */
interface IndustryMapper : BaseMapper<Industry> {
    // 该接口没有定义具体方法，子类可以通过实现此接口来扩展Industry的数据库操作。
}

interface InComMapper : BaseMapper<InCom>

interface MediaFileMapper : BaseMapper<MediaFile>