package org.mrmeowcat.poibackend.application.dto

data class UserDto(var username: String,
                   var roles: List<String>,
                   var enabled: Boolean,
                   var language: String,
                   var avatarFull: String? = null,
                   var avatarThumbnail: String? = null,
                   var avatarIcon: String? = null) : AbstractDto()