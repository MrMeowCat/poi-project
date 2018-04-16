package org.mrmeowcat.poibackend.application.security.handler

import org.mrmeowcat.poibackend.application.security.AuthUtils
import org.mrmeowcat.poibackend.config.SecurityConfig
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LogoutHandler : LogoutSuccessHandler {
    override fun onLogoutSuccess(request: HttpServletRequest,
                                 response: HttpServletResponse,
                                 authentication: Authentication?) {
        val jwtCookie = AuthUtils.createCookie(SecurityConfig.REMEMBER_ME_COOKIE_NAME, null, 0, true)
        val csrfCookie = AuthUtils.createCookie(SecurityConfig.CSRF_COOKIE_NAME, null, 0, false)
        response.addCookie(jwtCookie)
        response.addCookie(csrfCookie)
    }
}