package org.mrmeowcat.poibackend.application.security.filter

import org.mrmeowcat.poibackend.application.security.JwtUtils
import org.mrmeowcat.poibackend.application.security.JwtUtils.JWT_HEADER
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtProcessingFilter(authenticationManager: AuthenticationManager)
    : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {
        val token = request.getHeader(JWT_HEADER)

        if (token != null) {
            val authentication = JwtUtils.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain.doFilter(request, response)
    }
}