package org.mrmeowcat.poibackend.domain.repository

import org.mrmeowcat.poibackend.domain.document.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface UserRepository : MongoRepository<User, String> {

    fun findByName(name: String): Optional<User>

}