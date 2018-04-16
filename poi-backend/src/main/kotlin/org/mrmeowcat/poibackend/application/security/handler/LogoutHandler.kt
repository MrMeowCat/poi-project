package org.mrmeowcat.poibackend.application.security.handler

import org.mrmeowcat.poibackend.application.security.AuthUtils
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LogoutHandler : LogoutSuccessHandler {
    override fun onLogoutSuccess(request: HttpServletRequest,
                                 response: HttpServletResponse,
                                 authentication: Authentication?) {
        val cookie = Cookie(AuthUtils.REMEMBER_ME_COOKIE, null)
        cookie.domain = "localhost"
        cookie.path = "/"
        cookie.isHttpOnly = true
        cookie.maxAge = 0
        response.addCookie(cookie)
    }
}