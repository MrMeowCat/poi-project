package org.mrmeowcat.poibackend.domain.service

import org.mrmeowcat.poibackend.domain.document.AbstractDocument

/**
 * Basic CRUD service.
 */
interface DBService<T : AbstractDocument> {

    fun findById(id: String) : T

    fun findAll() : List<T>

    fun save(o: T)

    fun delete(o: T)

    fun deleteById(id: String)

    fun exists(o: T) : Boolean
}