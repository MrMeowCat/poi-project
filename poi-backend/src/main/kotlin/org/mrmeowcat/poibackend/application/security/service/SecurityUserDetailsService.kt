package org.mrmeowcat.poibackend.application.security.service

import org.mrmeowcat.poibackend.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SecurityUserDetailsService @Autowired
    constructor(private val userService: UserService) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.findByUsername(username)
        return SecurityUserDetails(user)
    }
}