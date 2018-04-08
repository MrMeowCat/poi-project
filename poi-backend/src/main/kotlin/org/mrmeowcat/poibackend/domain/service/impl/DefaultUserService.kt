package org.mrmeowcat.poibackend.domain.service.impl

import org.mrmeowcat.poibackend.config.AppConfig
import org.mrmeowcat.poibackend.domain.document.User
import org.mrmeowcat.poibackend.domain.exception.DocumentNotFoundException
import org.mrmeowcat.poibackend.domain.service.AbstractDBService
import org.mrmeowcat.poibackend.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

@Service
class DefaultUserService : AbstractDBService(), UserService {

    @Autowired private lateinit var appConfig: AppConfig

    override fun findById(id: String): User {
        return repositories.users.findById(id).orElseThrow { DocumentNotFoundException() }
    }

    override fun findByUsername(username: String): User {
        return repositories.users.findByUsername(username).orElseThrow { DocumentNotFoundException() }
    }

    override fun findAll(): List<User> {
        return repositories.users.findAll()
    }

    override fun save(o: User) {
        repositories.users.save(o)
        repositories.versions.createVersion(o)
    }

    override fun delete(o: User) {
        repositories.users.delete(o)
    }

    override fun exists(o: User): Boolean {
        return repositories.users.exists(Example.of(o))
    }

    override fun existsByUsername(username: String): Boolean {
        return repositories.users.existsByUsername(username)
    }

    override fun existsByEmail(email: String): Boolean {
        return repositories.users.existsByEmail(email)
    }

    override fun isCorrectEmail(email: String): Boolean {
        return Regex(appConfig.emailPattern).matches(email)
    }

    override fun isCorrectPassword(password: String): Boolean {
        return Regex(appConfig.passwordPattern).matches(password)
    }
}