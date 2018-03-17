package org.mrmeowcat.poibackend.application.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.mrmeowcat.poibackend.domain.document.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

internal object JwtUtils {
    const val JWT_HEADER = "Authorization"
    const val JWT_SCHEMA = "Bearer "
    const val JWT_EXPIRES = 86400000L
    private const val JWT_SECRET = "8fc4a0b6-b674-43ca-8ca1-a8836f6d8234"

    fun getAuthentication(token: String) : Authentication? {
        val tokenBody = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token.replace(JWT_SCHEMA, ""))
                .body

        val roles = tokenBody["roles"] as List<String>
        val mappedRoles = roles.mapTo(ArrayList()) { SimpleGrantedAuthority(it) }
        return UsernamePasswordAuthenticationToken(tokenBody.subject, null, mappedRoles)
    }

    fun createToken(user: User) : String {
        val claims = HashMap<String, Any>()
        claims.put("roles", user.roles)
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.name)
                .setExpiration(Date(System.currentTimeMillis() + JWT_EXPIRES))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact()
    }
}