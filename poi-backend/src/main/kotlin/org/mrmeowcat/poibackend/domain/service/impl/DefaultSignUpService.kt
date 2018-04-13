package org.mrmeowcat.poibackend.domain.service.impl

import com.google.common.base.Strings
import org.mrmeowcat.poibackend.application.dto.request.SignUpRequest
import org.mrmeowcat.poibackend.application.dto.response.SignUpValidationResponse
import org.mrmeowcat.poibackend.domain.document.User
import org.mrmeowcat.poibackend.domain.enums.Results
import org.mrmeowcat.poibackend.domain.enums.ValidationErrors
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

    override fun signUp(signUpRequest: SignUpRequest?) : SignUpValidationResponse {

        val errors: MutableList<String> = mutableListOf()
        signUpRequest ?: throw SignUpValidationException(
                response(Results.FAIL.value, listOf(ValidationErrors.FORM_EMPTY.value)))

        val username = signUpRequest.username
        val email = signUpRequest.email
        val password = signUpRequest.password
        val confirm = signUpRequest.confirm

        if (Strings.isNullOrEmpty(username)) {
            errors.add(ValidationErrors.USERNAME_EMPTY.value)
        }
        if (Strings.isNullOrEmpty(email)) {
            errors.add(ValidationErrors.EMAIL_EMPTY.value)
        }
        if (Strings.isNullOrEmpty(password)) {
            errors.add(ValidationErrors.PASSWORD_EMPTY.value)
        }
        if (Strings.isNullOrEmpty(confirm)) {
            errors.add(ValidationErrors.CONFIRM_EMPTY.value)
        }

        if (errors.isNotEmpty()) {
            throw SignUpValidationException(response(Results.FAIL.value, errors))
        }

        if (userService.existsByUsername(username!!)) {
            errors.add(ValidationErrors.USER_EXISTS.value)
            throw SignUpValidationException(response(Results.FAIL.value, errors))
        }
        if (userService.existsByEmail(email!!)) {
            errors.add(ValidationErrors.EMAIL_EXISTS.value)
            throw SignUpValidationException(response(Results.FAIL.value, errors))
        }
        if (!userService.isCorrectEmail(email)) {
            errors.add(ValidationErrors.EMAIL_INCORRECT.value)
            throw SignUpValidationException(response(Results.FAIL.value, errors))
        }
        if (!userService.isCorrectPassword(password!!)) {
            errors.add(ValidationErrors.PASSWORD_INCORRECT.value)
            throw SignUpValidationException(response(Results.FAIL.value, errors))
        }
        if (password != confirm) {
            errors.add(ValidationErrors.PASSWORD_MISMATCH.value)
            throw SignUpValidationException(response(Results.FAIL.value, errors))
        }

        val user = User()
        user.username = username
        user.password = passwordEncoder.encode(password)
        user.email = email
        user.enabled = true
        user.roles = listOf("USER")
        userService.save(user)
        return response(Results.OK.value, errors)
    }

    private fun response(status: String, errors: List<String>) : SignUpValidationResponse {
        return SignUpValidationResponse(status, errors)
    }
}