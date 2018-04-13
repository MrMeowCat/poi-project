package org.mrmeowcat.poibackend

import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.mrmeowcat.poibackend.application.dto.request.SignUpRequest
import org.mrmeowcat.poibackend.domain.document.User
import org.mrmeowcat.poibackend.domain.exception.SignUpValidationException
import org.mrmeowcat.poibackend.domain.service.SignUpService
import org.mrmeowcat.poibackend.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SignUpTests {

    @Autowired
    lateinit var signUpService: SignUpService

    @Autowired
    lateinit var userService: UserService

    @Before
    fun insertUser() {
        var user = User()
        user.username = "signup"
        user.email = "mail@bla.bla"
        userService.save(user)
    }

    @After
    fun deleteUsers() {
        val user1 = userService.findByUsername("signup")
        userService.delete(user1)
    }

    @Test fun test_signUp1() {
        try {
            signUpService.signUp(null)
        } catch (e: SignUpValidationException) {
            assertEquals("sign_up_null", e.message)
        }
    }

    @Test fun test_signUp2() {
        try {
            signUpService.signUp(SignUpRequest(null, null, null, null))
        } catch (e: SignUpValidationException) {
            assertEquals("username_empty", e.message)
        }
        try {
            signUpService.signUp(SignUpRequest("", null, null, null))
        } catch (e: SignUpValidationException) {
            assertEquals("username_empty", e.message)
        }
    }

    @Test fun test_signUp3() {
        try {
            signUpService.signUp(SignUpRequest("user", null, null, null))
        } catch (e: SignUpValidationException) {
            assertEquals("email_empty", e.message)
        }
        try {
            signUpService.signUp(SignUpRequest("user", "", null, null))
        } catch (e: SignUpValidationException) {
            assertEquals("email_empty", e.message)
        }
    }

    @Test fun test_signUp4() {
        try {
            signUpService.signUp(SignUpRequest("user", "mail@bla.bla", null, null))
        } catch (e: SignUpValidationException) {
            assertEquals("password_empty", e.message)
        }
        try {
            signUpService.signUp(SignUpRequest("user", "mail@bla.bla", "", null))
        } catch (e: SignUpValidationException) {
            assertEquals("password_empty", e.message)
        }
    }

    @Test fun test_signUp5() {
        try {
            signUpService.signUp(SignUpRequest("user", "mail@bla.bla", "password1", null))
        } catch (e: SignUpValidationException) {
            assertEquals("confirm_empty", e.message)
        }
        try {
            signUpService.signUp(SignUpRequest("user", "mail@bla.bla", "password1", ""))
        } catch (e: SignUpValidationException) {
            assertEquals("confirm_empty", e.message)
        }
    }

    @Test fun test_signUp6() {
        try {
            signUpService.signUp(SignUpRequest("signup", "mail@bla.bla", "password1", "password1"))
        } catch (e: SignUpValidationException) {
            assertEquals("user_exists", e.message)
        }

        try {
            signUpService.signUp(SignUpRequest("signup123", "mail@bla.bla", "password1", "password1"))
        } catch (e: SignUpValidationException) {
            assertEquals("email_exists", e.message)
        }
    }

    @Test(expected = SignUpValidationException::class) fun test_signUp7() {
        signUpService.signUp(SignUpRequest("1", "qq", "q", "1"))
    }

    @Test(expected = SignUpValidationException::class) fun test_signUp8() {
        signUpService.signUp(SignUpRequest("1", "!!@E2d12", "q", "1"))
    }

    @Test(expected = SignUpValidationException::class) fun test_signUp9() {
        signUpService.signUp(SignUpRequest("1", "mail1@bla.bla", "q", "1"))
    }

    @Test(expected = SignUpValidationException::class) fun test_signUp_10() {
        signUpService.signUp(SignUpRequest("1", "mail1@bla.bla", "qwertyui", "1"))
    }

    @Test fun test_signUp_11() {
        try {
            signUpService.signUp(SignUpRequest("signup1", "mail1@bla.bla", "password1", "password12"))
        } catch (e: SignUpValidationException) {
            assertEquals("password_mismatch", e.message)
        }
    }

    @Test
    fun test_signUp_12() {
        signUpService.signUp(SignUpRequest("signup1", "mail1@bla.bla", "password1", "password1"))
        val user = userService.findByUsername("signup1")
        println(user)
        userService.delete(user)
    }
}