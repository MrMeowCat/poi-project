package org.mrmeowcat.poibackend

import org.junit.Test
import org.junit.runner.RunWith
import org.mrmeowcat.poibackend.application.dto.UserDto
import org.mrmeowcat.poibackend.domain.document.User
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
	fun test_saveUser() {

        var user = userService.findByName("test3")
        user.roles = mutableListOf("USER", "GUEST", "EDITOR")
        userService.save(user)

	}

    @Test
    fun test_createUser() {
        var user = User()
        user.name = "test"
        user.email = "test"
        userService.save(user)
    }

    @Test
    fun test_getUser() {
        var user = userService.findByName("test")
        println(user)

        var userDto = UserDto("asd", mutableListOf(), true)
        println(userDto)
    }
}
