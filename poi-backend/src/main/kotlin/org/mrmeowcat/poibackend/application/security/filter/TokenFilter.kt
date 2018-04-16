package org.mrmeowcat.poibackend.application.security.filter

import org.mrmeowcat.poibackend.application.security.AuthUtils
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenFilter(authenticationManager: AuthenticationManager)
    : BasicAuthenticationFilter(authenticationManager) {

    companion object {
        @JvmField val LOGGER = LoggerFactory.getLogger(TokenFilter::class.java)
    }

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {
        val token = AuthUtils.getJwtToken(request)

        if (token != null) {
            if (!AuthUtils.validateCsrfTokens(request)) {
                LOGGER.warn("Invalid CSRF token for request: ${request.servletPath}")
                chain.doFilter(request, response)
                return
            }

            val authentication = AuthUtils.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain.doFilter(request, response)
    }
}