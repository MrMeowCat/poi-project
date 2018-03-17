package org.mrmeowcat.poibackend.domain.service

interface DBService<T> {

    fun findById(id: String) : T
    fun findAll() : List<T>
    fun save(o: T)
    fun delete(o: T)
    fun exists(o: T) : Boolean
}