package org.mrmeowcat.poibackend.application.security.filter

import org.mrmeowcat.poibackend.application.security.AuthUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenFilter(authenticationManager: AuthenticationManager)
    : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {
        val token = getToken(request)

        if (token != null) {
            val authentication = AuthUtils.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain.doFilter(request, response)
    }

    private fun getToken(request: HttpServletRequest) : String? {
        val token = request.getHeader(AuthUtils.AUTHORIZATION_HEADER)

        if (token != null) return token
        if (request.cookies == null) return null

        for (cookie in request.cookies) {
            if (cookie.name == AuthUtils.REMEMBER_ME_COOKIE) {
                return cookie.value
            }
        }

        return null
    }
}