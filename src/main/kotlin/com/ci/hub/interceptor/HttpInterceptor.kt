package com.ci.hub.interceptor

import cn.hutool.core.date.DateUtil
import cn.hutool.jwt.JWTUtil.parseToken
import cn.hutool.jwt.JWTUtil.verify
import com.ci.hub.common.AuthAccess
import com.ci.hub.common.HttpStatus
import com.ci.hub.entity.User
import com.ci.hub.exception.ServiceException
import com.ci.hub.server.IUserService
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor


/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
/**
 * http请求拦截器
 */

class HttpInterceptor : HandlerInterceptor {

    @Resource
    private lateinit var userService: IUserService
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        //拦截注解为@Auth的接口
        println("请求发出$handler")
        if (handler is HandlerMethod) {
            println("handler=${handler.beanType}")
            val authAccess = handler.method.getAnnotation(AuthAccess::class.java)
            println("authAccess=$authAccess")
            if (authAccess!= null) {
                return true
            }
        }
        //获取header中的token信息
        val token = request.getHeader("token")
        //判断token是否有效
        if (token.isNullOrEmpty()) {
            //无效token，返回401错误
            throw ServiceException("无效token", HttpStatus.UNAUTHORIZED)
        }
        //解析token
        val jwt = try {
            parseToken(token)
        } catch (e: Exception) {
            throw ServiceException("token解析失败", HttpStatus.UNAUTHORIZED)
        }

        //验证过期时间
        if (jwt.validate(DateUtil.currentSeconds())) {
            //过期时间已过，返回401错误
            throw ServiceException("token已过期", HttpStatus.UNAUTHORIZED)
        }
        val uname = jwt.getPayload(User::username.name)?.toString() ?: ""
        val user = userService.getUser(uname)
            ?: //用户不存在，返回401错误
            throw ServiceException("用户不存在", HttpStatus.UNAUTHORIZED)

        //验证token
        if (!verify(token, user.upwd.toByteArray())) {
            //token验证失败，返回401错误
            throw ServiceException("token验证失败", HttpStatus.UNAUTHORIZED)
        }

        return true
    }
}
