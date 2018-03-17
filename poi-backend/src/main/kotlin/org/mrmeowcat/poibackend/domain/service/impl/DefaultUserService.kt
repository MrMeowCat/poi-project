package org.mrmeowcat.poibackend.domain.service.impl

import org.mrmeowcat.poibackend.domain.document.User
import org.mrmeowcat.poibackend.domain.exception.DocumentNotFoundException
import org.mrmeowcat.poibackend.domain.service.AbstractDBService
import org.mrmeowcat.poibackend.domain.service.UserService
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

@Service
class DefaultUserService : AbstractDBService(), UserService {

    override fun findById(id: String): User {
        return repositories.users.findById(id).orElseThrow { DocumentNotFoundException() }
    }

    override fun findByName(name: String): User {
        return repositories.users.findByName(name).orElseThrow { DocumentNotFoundException() }
    }

    override fun findAll(): List<User> {
        return repositories.users.findAll()
    }

    override fun save(o: User) {
        repositories.users.save(o)
    }

    override fun delete(o: User) {
        repositories.users.delete(o)
    }

    override fun exists(o: User): Boolean {
        return repositories.users.exists(Example.of(o))
    }
}