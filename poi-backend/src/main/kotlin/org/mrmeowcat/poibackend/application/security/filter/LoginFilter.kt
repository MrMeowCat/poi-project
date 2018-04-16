package org.mrmeowcat.poibackend.application.security.filter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.mrmeowcat.poibackend.application.dto.request.Credentials
import org.mrmeowcat.poibackend.application.security.AuthUtils
import org.mrmeowcat.poibackend.application.security.service.SecurityUserDetails
import org.mrmeowcat.poibackend.config.SecurityConfig
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginFilter internal constructor(path: String, authenticationManager: AuthenticationManager)
    : UsernamePasswordAuthenticationFilter() {

    init {
        setAuthenticationManager(authenticationManager)
        setFilterProcessesUrl(path)
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse)
            : Authentication {
        val credentials: Credentials = jacksonObjectMapper().readValue(request.inputStream)
        return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        credentials.username,
                        credentials.password)
        )
    }

    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          chain: FilterChain,
                                          authResult: Authentication) {
        val user = (authResult.principal as SecurityUserDetails).user
        val token = AuthUtils.createJwtToken(user)
        val csrfToken = AuthUtils.createCsrfToken()
        val rememberMe = AuthUtils.getRememberMeParamValue(request)
        val age = if (rememberMe) SecurityConfig.AUTH_COOKIE_AGE / 1000 else null

        val jwtCookie = AuthUtils.createCookie(SecurityConfig.REMEMBER_ME_COOKIE_NAME, token, age, true)
        val csrfCookie = AuthUtils.createCookie(SecurityConfig.CSRF_COOKIE_NAME, csrfToken, age, false)

        response.addCookie(jwtCookie)
        response.addCookie(csrfCookie)
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest,
                                            response: HttpServletResponse,
                                            failed: AuthenticationException) {
        response.status = HttpStatus.FORBIDDEN.value()
    }
}