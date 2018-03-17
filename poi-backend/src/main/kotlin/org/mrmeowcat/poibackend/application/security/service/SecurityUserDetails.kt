package org.mrmeowcat.poibackend.application.security.service

import org.mrmeowcat.poibackend.domain.document.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUserDetails(val user: User) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>
    = user.roles.mapTo(ArrayList()) {
        SimpleGrantedAuthority(it)
    }

    override fun isEnabled() = user.enabled

    override fun getUsername() = user.name

    override fun getPassword() = user.password

    override fun isCredentialsNonExpired() = true

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}