package org.mrmeowcat.poibackend.application.controller.v1

import org.mrmeowcat.poibackend.application.mapper.BeanMapper
import org.mrmeowcat.poibackend.domain.document.User
import org.mrmeowcat.poibackend.domain.exception.DocumentNotFoundException
import org.mrmeowcat.poibackend.domain.repository.Repositories
import org.mrmeowcat.poibackend.domain.service.Services
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

/**
 * Basic REST controller.
 */
@Component
abstract class AbstractController {
    @Autowired protected lateinit var repositories: Repositories
    @Autowired protected lateinit var services: Services
    @Autowired protected lateinit var mapper: BeanMapper

    companion object {
        @JvmField val LOGGER = LoggerFactory.getLogger(AbstractController::class.java)
    }

    protected fun getCurrentUser() : User? {
        val username = SecurityContextHolder.getContext().authentication.name
        val user: User?

        try {
            user = services.users.findByUsername(username)
        } catch (e: DocumentNotFoundException) {
            LOGGER.warn("Request is unauthorized - no user found.")
            return null
        }

        return user
    }
}