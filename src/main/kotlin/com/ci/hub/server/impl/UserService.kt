package com.ci.hub.server.impl

import com.ci.hub.entity.User
import com.ci.hub.entity.UserGroup
import com.ci.hub.exception.ServiceException
import com.ci.hub.mapper.GroupMapper
import com.ci.hub.mapper.UserMapper
import com.ci.hub.server.IUserService
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService : ServiceImpl<UserMapper, User>(), IUserService {

    @Autowired
    lateinit var groupMapper: GroupMapper

    override fun createUser(user: User): Boolean {
        try {
            return saveOrUpdate(user)
        } catch (e: Exception) {
            throw ServiceException("用户已存在")
        }
    }

    override fun createGroup(group: UserGroup): Boolean {
        try {
            return groupMapper.insert(group) > 0
        } catch (e: Exception) {
            throw ServiceException("用户组已存在")
        }
    }

    override fun getUserById(id: Long): User? {
        return getOne(User::id.eq(id))
    }

    //根据openid查询用户信息
    override fun getUserByOpenid(openid: String): User? {
        return getOne(User::openid.eq(openid))
    }

    //查询所有用户
    override fun getAllUser(): List<User> {
        return list()
    }

    override fun getUser(uname: String): User? {
        return getOne(User::username.eq(uname))
    }

    override fun updateUser(user: User): Boolean {
        return updateById(user)
    }

    override fun deleteUser(userId: Long): Boolean {
        return removeById(userId)
    }
}
