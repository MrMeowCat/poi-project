package org.mrmeowcat.poibackend.domain.service.impl

import com.google.common.base.Strings
import org.mrmeowcat.poibackend.application.dto.SignUpRequest
import org.mrmeowcat.poibackend.domain.document.User
import org.mrmeowcat.poibackend.domain.exception.SignUpValidationException
import org.mrmeowcat.poibackend.domain.service.AbstractDBService
import org.mrmeowcat.poibackend.domain.service.SignUpService
import org.mrmeowcat.poibackend.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class DefaultSignUpService : AbstractDBService(), SignUpService {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun signUp(signUpRequest: SignUpRequest?) {

        signUpRequest ?: throw SignUpValidationException("sign_up_null")

        val username = signUpRequest.username
        val email = signUpRequest.email
        val password = signUpRequest.password
        val confirm = signUpRequest.confirm

        if (Strings.isNullOrEmpty(username)) {
            throw SignUpValidationException("username_empty")
        }
        if (Strings.isNullOrEmpty(email)) {
            throw SignUpValidationException("email_empty")
        }
        if (Strings.isNullOrEmpty(password)) {
            throw SignUpValidationException("password_empty")
        }
        if (Strings.isNullOrEmpty(confirm)) {
            throw SignUpValidationException("confirm_empty")
        }
        if (userService.existsByUsername(username!!)) {
            throw SignUpValidationException("user_exists")
        }
        if (userService.existsByEmail(email!!)) {
            throw SignUpValidationException("email_exists")
        }
        if (!userService.isCorrectEmail(email)) {
            throw SignUpValidationException("email_incorrect")
        }
        if (!userService.isCorrectPassword(password!!)) {
            throw SignUpValidationException("password_incorrect")
        }
        if (password != confirm) {
            throw SignUpValidationException("password_mismatch")
        }

        val user = User()
        user.username = username
        user.password = passwordEncoder.encode(password)
        user.email = email
        user.enabled = true
        user.roles = listOf("USER")
        userService.save(user)
    }
}