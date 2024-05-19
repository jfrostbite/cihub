package com.ci.hub.controller.impl

import cn.hutool.core.util.StrUtil
import com.ci.hub.common.AuthAccess
import com.ci.hub.common.Result
import com.ci.hub.controller.IUserController
import com.ci.hub.entity.User
import com.ci.hub.server.impl.AuthService
import com.ci.hub.server.impl.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/07
 */
@RestController
@RequestMapping("user")
class UserController : IUserController {

    @Autowired
    lateinit var service: UserService

    @Autowired
    lateinit var authService: AuthService

    @PostMapping
    @AuthAccess
    override fun createUser(@RequestBody user: User): Result<String> {
        val createUser = service.createUser(user)
        if (!createUser) {
            return Result.Error("创建失败")
        }
        return Result.Success<String>("创建成功")
    }

    override fun createUserByOpenId(openId: String, phone: String?, weid: String): Result<User> {
        //根据openid 创建新用户
        val user = User(openid = openId, phone = phone ?: "", weid = weid)
        val createUser = service.createUser(user)
        if (!createUser) {            //创建失败
            return Result.Error("创建失败")
        }
        return Result.Success<User>(user)
    }

    @PostMapping("login/admin")
    @AuthAccess
    override fun adminLogin(@RequestBody user: User): Result<User> {
        if (StrUtil.isAllNotEmpty(user.username, user.upwd)) {
            return Result.Success<User>(authService.login(user.username, user.upwd))
        }
        return Result.Error("用户名或密码错误")
    }

    @PostMapping("login")
    @AuthAccess
    override fun userLogin(@RequestParam openId: String, @RequestParam sessionKey: String): Result<User> {
        try {
            TODO("Not yet implemented")
        } catch (e: Exception) {
            return Result.Error("登录失败")
        }
    }

    @GetMapping("{userId}")
    override fun getUserInfo(@PathVariable userId: Long): Result<User> {
        val user = service.getUserById(userId)
            ?: return Result.Error("用户不存在")            //用户不存在
        return Result.Success<User>(user)
    }

    @GetMapping
    override fun getUserList(): Result<List<User>> {
        val allUser = service.getAllUser()
        return Result.Success<List<User>>(allUser)
    }

    @GetMapping("router")
    @AuthAccess
    override fun getRouter(): String {
        return """
            {
              "success": true,
              "message": "成功",
              "code": 200,
              "data": [
                {
                  "path": "/bas",
                  "name": "",
                  "redirect": "",
                  "component": "",
                  "meta": {
                    "title": "系统管理",
                    "icon": "ep:setting",
                    "showLink": true,
                    "rank": 1,
                    "keepAlive": false,
                    "frameSrc": ""
                  },
                  "children": [
                    {
                      "path": "/bas/post/index",
                      "name": "BasPostManger",
                      "redirect": "",
                      "component": "basic/post/index",
                      "meta": {
                        "title": "岗位管理",
                        "icon": "",
                        "showLink": true,
                        "showParent": true,
                        "keepAlive": false,
                        "frameSrc": ""
                      }
                    }
                  ]
                }
              ],
              "timestamp": 1715499440599
            }
        """.trimIndent()
    }
}