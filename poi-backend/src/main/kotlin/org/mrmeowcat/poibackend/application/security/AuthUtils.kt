package org.mrmeowcat.poibackend.application.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.commons.lang3.RandomStringUtils
import org.mrmeowcat.poibackend.domain.document.User
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
    const val REMEMBER_ME_COOKIE = "rememberme"
    const val REMEMBER_ME_EXPIRES = 86400000
    const val JWT_EXPIRES = 86400000
    const val CSRF_TOKEN_HEADER = "X-CSRF-TOKEN"
    const val CSRF_TOKEN_COOKIE = "csrftoken"
    const val CSRF_TOKEN_LENGTH = 64
    private const val JWT_SECRET = "8fc4a0b6-b674-43ca-8ca1-a8836f6d8234"

    fun getRememberMeParamValue(request: HttpServletRequest) : Boolean {
        val rememberMe = request.getParameter(REMEMBER_ME_PARAM)
        return rememberMe?.toBoolean() == true
    }

    fun getAuthentication(token: String) : Authentication? {
        val tokenBody = Jwts.parser()
                .setSigningKey(JWT_SECRET)
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
                .setExpiration(Date(System.currentTimeMillis() + JWT_EXPIRES))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact()
    }

    fun getJwtToken(request: HttpServletRequest) : String? {
        if (request.cookies == null) return null

        for (cookie in request.cookies) {
            if (cookie.name == REMEMBER_ME_COOKIE) {
                return cookie.value
            }
        }

        return null
    }

    fun createCsrfToken() : String {
        return RandomStringUtils.randomAlphanumeric(CSRF_TOKEN_LENGTH)
    }

    fun validateCsrfTokens(request: HttpServletRequest) : Boolean {
        val headerToken = request.getHeader(CSRF_TOKEN_HEADER) ?: return false

        for (cookie in request.cookies) {
            if (cookie.name == CSRF_TOKEN_COOKIE) {
                return cookie.value == headerToken
            }
        }

        return false
    }

    fun createCookie(name: String, value: String?, age: Int?, httpOnly: Boolean) : Cookie {
        val cookie = Cookie(name, value)
        cookie.isHttpOnly = httpOnly
        cookie.domain = "localhost"
        cookie.path = "/"

        if (age != null) {
            cookie.maxAge = age
        }

        return cookie
    }
}