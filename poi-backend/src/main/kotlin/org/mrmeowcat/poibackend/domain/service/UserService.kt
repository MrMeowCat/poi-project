package org.mrmeowcat.poibackend.domain.service

import org.mrmeowcat.poibackend.domain.document.User

interface UserService : DBService<User> {

    fun findByUsername(username: String) : User

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun isCorrectEmail(email: String): Boolean

    fun isCorrectPassword(password: String): Boolean
}