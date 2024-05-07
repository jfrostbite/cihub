package com.ci.hub.server.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.ci.hub.common.CiHubColumn
import com.ci.hub.entity.User
import com.ci.hub.entity.UserGroup
import com.ci.hub.mapper.GroupMapper
import com.ci.hub.mapper.UserMapper
import com.ci.hub.server.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService : IUserService {

    @Autowired
    lateinit var userMapper: UserMapper

    @Autowired
    lateinit var groupMapper: GroupMapper

    override fun createUser(user: User): Boolean {
        return userMapper.insert(user) > 0
    }

    override fun createGroup(group: UserGroup): Boolean {
        return groupMapper.insert(group) > 0
    }

    override fun getUserById(id: Long): User? {
        return userMapper.selectById(id)
    }

    //根据openid查询用户信息
    override fun getUserByOpenid(openid: String): User? {
        return userMapper.selectOne(QueryWrapper<User>().eq(CiHubColumn.OPENID, openid))
    }

    //查询所有用户
    override fun getAllUser(): List<User> {
        return userMapper.selectList(null)
    }
}
