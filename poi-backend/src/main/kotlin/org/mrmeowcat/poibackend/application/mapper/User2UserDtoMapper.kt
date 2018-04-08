package org.mrmeowcat.poibackend.application.mapper

import org.mrmeowcat.poibackend.application.dto.UserDto
import org.mrmeowcat.poibackend.domain.document.User
import org.springframework.stereotype.Component

@Component
internal class User2UserDtoMapper : Mapper<User, UserDto> {

    override fun map(e: User): UserDto {
        val id = e.id
        val username = e.username
        val roles = e.roles
        val enabled = e.enabled
        val user = UserDto(username, roles, enabled)
        user.id = e.id
        return user
    }
}