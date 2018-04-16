package org.mrmeowcat.poibackend.application.security.handler

import org.mrmeowcat.poibackend.application.security.AuthUtils
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LogoutHandler : LogoutSuccessHandler {
    override fun onLogoutSuccess(request: HttpServletRequest,
                                 response: HttpServletResponse,
                                 authentication: Authentication?) {
        val jwtCookie = AuthUtils.createCookie(AuthUtils.REMEMBER_ME_COOKIE, null, 0, true)
        val csrfCookie = AuthUtils.createCookie(AuthUtils.CSRF_TOKEN_COOKIE, null, 0, false)
        response.addCookie(jwtCookie)
        response.addCookie(csrfCookie)
    }
}