package com.ci.hub

import com.ci.hub.entity.UserGroup
import com.ci.hub.server.impl.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CiHubApplicationTests {

    @Autowired
    lateinit var service: UserService

    @Test
    fun contextLoads() {
//        service.createUser(User(uname="test",age=25, phone = "", upwd="123456"))
        service.createGroup(UserGroup(name = "VIP"))

    }

}
