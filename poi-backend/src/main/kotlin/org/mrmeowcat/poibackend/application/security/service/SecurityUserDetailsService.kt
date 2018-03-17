package org.mrmeowcat.poibackend.application.security.service

import org.mrmeowcat.poibackend.domain.service.impl.Services
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SecurityUserDetailsService @Autowired
    constructor(private val services: Services) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = services.users.findByName(username)
        return SecurityUserDetails(user)
    }
}