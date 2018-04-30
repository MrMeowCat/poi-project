package org.mrmeowcat.poibackend.application.mapper

import org.mrmeowcat.poibackend.application.dto.UserDto
import org.mrmeowcat.poibackend.domain.document.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Mapper for user documents.
 */
@Component
class User2UserDtoMapper @Autowired constructor(val mapper: BeanMapper)
    : Mapper<User, UserDto> {

    override fun map(e: User): UserDto {
        val username = e.username!!
        val roles = e.roles
        val enabled = e.enabled
        val language = e.language
        val avatarFull = e.avatarFull
        val avatarThumbnail = e.avatarThumbnail
        val avatarIcon = e.avatarIcon

        val user = UserDto(username, roles, enabled, language, avatarFull, avatarThumbnail, avatarIcon)
        user.id = e.id
        return user
    }
}