package org.mrmeowcat.poibackend.domain.service.impl

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.mrmeowcat.poibackend.domain.document.Theme
import org.mrmeowcat.poibackend.domain.exception.DocumentNotFoundException
import org.mrmeowcat.poibackend.domain.exception.JsonException
import org.mrmeowcat.poibackend.domain.service.AbstractDBService
import org.mrmeowcat.poibackend.domain.service.ThemeService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import java.io.IOException

/**
 *  Mongo theme service implementation.
 */
@Service
class MongoThemeService : AbstractDBService(), ThemeService {

    companion object {
        @JvmField val LOGGER = LoggerFactory.getLogger(MongoThemeService::class.java)
    }

    @Autowired
    lateinit var objectMapper: ObjectMapper

    override fun findByName(name: String?): List<Theme> {
        return repositories.themes.findByName(name)
    }

    override fun findByUserId(id: String?): List<Theme> {
        return repositories.themes.findByUserId(id)
    }

    override fun findByIdAndUserId(id: String?, userId: String?): Theme? {
        return repositories.themes.findByIdAndUserId(id, userId)
    }

    override fun existsByName(name: String?): Boolean {
        return repositories.themes.existsByName(name)
    }

    override fun existsByIdAndUserId(id: String?, userId: String?): Boolean {
        return repositories.themes.existsByIdAndUserId(id, userId)
    }

    override fun findById(id: String): Theme {
        return repositories.themes.findById(id).orElseThrow({
            DocumentNotFoundException("Theme not found - id: $id")
        })
    }

    override fun findAll(): List<Theme> {
        return repositories.themes.findAll()
    }

    override fun save(o: Theme) {
        val previousVersion = repositories.versions.previousVersion(o)
        if (o == previousVersion) return

        try {
            o.style = minifyJson(o.style!!)
        } catch (e: IOException) {
            LOGGER.error("Failed to minify theme style: ${o.style}")
            throw JsonException()
        }

        repositories.themes.save(o)
        repositories.versions.createVersion(o)
    }

    override fun delete(o: Theme) {
        repositories.themes.delete(o)
    }

    override fun deleteById(id: String) {
        repositories.themes.deleteById(id)
    }

    override fun exists(o: Theme): Boolean {
        return repositories.themes.exists(Example.of(o))
    }

    private fun minifyJson(json: String): String =
            objectMapper.readValue(json, JsonNode::class.java).toString()
}