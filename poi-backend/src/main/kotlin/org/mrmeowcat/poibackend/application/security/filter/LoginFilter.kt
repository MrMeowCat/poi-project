package org.mrmeowcat.poibackend.application.security.filter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.mrmeowcat.poibackend.application.dto.request.Credentials
import org.mrmeowcat.poibackend.application.security.AuthUtils
import org.mrmeowcat.poibackend.application.security.service.SecurityUserDetails
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
        val age = if (rememberMe) AuthUtils.REMEMBER_ME_EXPIRES / 1000 else null

        val jwtCookie = AuthUtils.createCookie(AuthUtils.REMEMBER_ME_COOKIE, token, age, true)
        val csrfCookie = AuthUtils.createCookie(AuthUtils.CSRF_TOKEN_COOKIE, csrfToken, age, false)

        response.addCookie(jwtCookie)
        response.addCookie(csrfCookie)
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest,
                                            response: HttpServletResponse,
                                            failed: AuthenticationException) {
        response.status = HttpStatus.FORBIDDEN.value()
    }
}