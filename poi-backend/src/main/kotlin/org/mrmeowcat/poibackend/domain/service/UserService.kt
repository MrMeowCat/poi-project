package org.mrmeowcat.poibackend.domain.service

import org.mrmeowcat.poibackend.domain.document.User

interface UserService : DBService<User> {

    fun findByName(name: String) : User
}