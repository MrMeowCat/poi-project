package org.mrmeowcat.poibackend.domain.repository

import org.mrmeowcat.poibackend.domain.document.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

/**
 * Mongo user CRUD repository.
 */
interface MongoUserRepository : MongoRepository<User, String> {

    fun findByUsername(username: String): Optional<User>

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean
}