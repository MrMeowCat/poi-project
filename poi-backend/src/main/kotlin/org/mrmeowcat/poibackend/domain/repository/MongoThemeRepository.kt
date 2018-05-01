package org.mrmeowcat.poibackend.domain.repository

import org.mrmeowcat.poibackend.domain.document.Theme
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Mongo theme CRUD repository.
 */
interface MongoThemeRepository : MongoRepository<Theme, String> {

    fun findByName(name: String) : List<Theme>

    fun findByUserId(id: String) : List<Theme>

    fun findByIdAndUserId(id: String?, userId: String?) : Theme?

    fun existsByName(name: String) : Boolean

    fun existsByIdAndUserId(id: String?, userId: String?) : Boolean
}