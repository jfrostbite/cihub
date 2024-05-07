package com.ci.hub.controller.impl

import com.ci.hub.common.Result
import com.ci.hub.controller.IUserController
import com.ci.hub.entity.User
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
class UserController: IUserController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping
    override fun createUser(@RequestBody user: User): Result<String> {
        TODO("Not yet implemented")
    }

    @PostMapping("admin")
    override fun adminLogin(@RequestParam username: String, @RequestParam password: String): Result<String> {
        TODO("Not yet implemented")
    }

    @PostMapping("login")
    override fun userLogin(@RequestParam openId: String, @RequestParam sessionKey: String): Result<String> {
        TODO("Not yet implemented")
    }

    @GetMapping("{userId}")
    override fun getUserInfo(@PathVariable userId: Long): Result<User> {
        TODO("Not yet implemented")
    }

    @GetMapping
    override fun getUserList(): Result<List<User>> {
        val allUser = userService.getAllUser()
        return Result.Success<List<User>>(allUser)
    }
}