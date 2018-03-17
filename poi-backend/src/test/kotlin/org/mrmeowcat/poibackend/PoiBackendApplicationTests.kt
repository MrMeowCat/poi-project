package org.mrmeowcat.poibackend

import org.junit.Test
import org.junit.runner.RunWith
import org.mrmeowcat.poibackend.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class PoiBackendApplicationTests {

    @Autowired
    lateinit var userService: UserService

	@Test
	fun contextLoads() {

        var user = userService.findByName("user")
        user.roles = mutableListOf("USER", "GUEST")
        userService.save(user)

	}

}
