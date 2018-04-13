package org.mrmeowcat.poibackend

import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mrmeowcat.poibackend.application.dto.UserDto
import org.mrmeowcat.poibackend.domain.document.User
import org.mrmeowcat.poibackend.domain.service.UserService
import org.mrmeowcat.poibackend.domain.service.impl.Services
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserTests {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var services: Services

	@Test
	fun test_saveUser() {

        var user = userService.findByUsername("user")
        user.roles = mutableListOf("USER", "GUEST")
        user.password = "1"
        userService.save(user)

	}

    @Test
    fun test_createUser() {
        var user = User()
        user.username = "user"
        userService.save(user)
    }

    @Test
    fun test_getUser() {
        var user = userService.findByUsername("test")
        println(user)

        var userDto = UserDto("asd", mutableListOf(), true)
        println(userDto)
    }

    @Test
    fun test_userExists() {
        println(userService.existsByUsername("user"))
    }

    @Test
    fun test_regex() {
        assertTrue(userService.isCorrectEmail("ass.adf@qwe.com"))
        assertTrue(userService.isCorrectPassword("asdfq1q1"))
    }
}
