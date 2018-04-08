package org.mrmeowcat.poibackend.application.security.filter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.mrmeowcat.poibackend.application.dto.Credentials
import org.mrmeowcat.poibackend.application.security.JwtUtils
import org.mrmeowcat.poibackend.application.security.service.SecurityUserDetails
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter internal constructor(path: String, authenticationManager: AuthenticationManager)
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
        val token = JwtUtils.createToken(user)
        response.addHeader(JwtUtils.JWT_HEADER, JwtUtils.JWT_SCHEMA + token)
        response.addHeader("Content-type", MediaType.APPLICATION_JSON_VALUE)
        response.addHeader("Access-Control-Expose-Headers", JwtUtils.JWT_HEADER)
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest,
                                            response: HttpServletResponse,
                                            failed: AuthenticationException) {
        response.status = HttpStatus.FORBIDDEN.value()
    }
}