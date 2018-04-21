package org.mrmeowcat.poibackend.application.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.mrmeowcat.poibackend.config.SecurityConfig
import org.mrmeowcat.poibackend.domain.document.User
import org.mrmeowcat.poibackend.util.StringUtils
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

internal object AuthUtils {

    const val REMEMBER_ME_PARAM = "rememberMe"

    fun getRememberMeParamValue(request: HttpServletRequest) : Boolean {
        val rememberMe = request.getParameter(REMEMBER_ME_PARAM)
        return rememberMe?.toBoolean() == true
    }

    fun getAuthentication(token: String) : Authentication? {
        val tokenBody = Jwts.parser()
                .setSigningKey(SecurityConfig.JWT_SECRET)
                .parseClaimsJws(token)
                .body

        val roles = tokenBody["roles"] as List<String>
        val mappedRoles = roles.mapTo(ArrayList()) { SimpleGrantedAuthority(it) }
        return UsernamePasswordAuthenticationToken(tokenBody.subject, null, mappedRoles)
    }

    fun createJwtToken(user: User) : String {
        val claims = HashMap<String, Any>()
        claims.put("roles", user.roles)
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.username)
                .setExpiration(Date(System.currentTimeMillis() + SecurityConfig.JWT_AGE))
                .signWith(SignatureAlgorithm.HS256, SecurityConfig.JWT_SECRET)
                .compact()
    }

    fun getJwtToken(request: HttpServletRequest) : String? {
        if (request.cookies == null) return null

        for (cookie in request.cookies) {
            if (cookie.name == SecurityConfig.REMEMBER_ME_COOKIE_NAME) {
                return cookie.value
            }
        }

        return null
    }

    fun createCsrfToken() : String {
        return StringUtils.randomAlphanumeric(SecurityConfig.CSRF_TOKEN_LENGTH)
    }

    /**
     * CSRF protection pattern
     * Request should contain both cookie/jwt claim and header with csrf token
     */
    fun validateCsrfTokens(request: HttpServletRequest) : Boolean {
        val headerToken = request.getHeader(SecurityConfig.CSRF_TOKEN_HEADER) ?: return false

        for (cookie in request.cookies) {
            if (cookie.name == SecurityConfig.CSRF_COOKIE_NAME) {
                return cookie.value == headerToken
            }
        }

        return false
    }

    fun createCookie(name: String, value: String?, age: Int?, httpOnly: Boolean) : Cookie {
        val cookie = Cookie(name, value)
        cookie.isHttpOnly = httpOnly
        cookie.secure = SecurityConfig.COOKIE_SECURE
        cookie.domain = SecurityConfig.COOKIE_DOMAIN
        cookie.path = "/"

        if (age != null) {
            cookie.maxAge = age
        }

        return cookie
    }
}