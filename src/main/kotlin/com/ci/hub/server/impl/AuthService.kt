package com.ci.hub.server.impl

import cn.hutool.core.date.DateUtil
import cn.hutool.jwt.JWT
import com.ci.hub.common.AD
import com.ci.hub.common.CI
import com.ci.hub.common.WX
import com.ci.hub.entity.User
import com.ci.hub.exception.ServiceException
import com.ci.hub.mapper.UserMapper
import com.ci.hub.server.IAuthService
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service


/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/10
 */
@Service
class AuthService : ServiceImpl<UserMapper, User>(), IAuthService {

    override fun login(username: String, password: String): User {
        val user = try {
            getOne(User::username.eq(username))
        } catch (e: Exception) {
            throw ServiceException(e.message ?: "登录失败")
        } ?: throw ServiceException("用户名或密码错误")
        if (user.upwd != password) {
            throw ServiceException("用户名或密码错误")
        }
        //生成token
        val token = JWT.create()
            .setPayload(User::username.name, username)
            .setPayload(User::vip.name, user.vip)
            .setPayload(User::groupid.name, user.groupid)
            .setKey(password.toByteArray())
            .setSubject(AD)
            .setIssuer(CI)
            .setExpiresAt(DateUtil.offsetDay(DateUtil.date(), 7))
            .sign()

        return user.apply { this.token = token }
    }

    override fun wxLogin(openId: String, sessionKey: String): User {
        //查询用户是否存在
        val user = try {
            getOne(User::openid.eq(openId))
        } catch (e: Exception) {
            throw ServiceException("微信登录失败")
        } ?: throw ServiceException("微信登录失败")
        //生成token
        val token = JWT.create()
            .setPayload(User::username.name, user.username)
            .setPayload(User::vip.name, user.vip)
            .setPayload(User::groupid.name, user.groupid)
            .setKey(sessionKey.toByteArray())
            .setSubject(WX)
            .setIssuer(CI)
            .setExpiresAt(DateUtil.offsetDay(DateUtil.date(), 7))
            .sign()

        return user.apply { this.token = token }
    }
}