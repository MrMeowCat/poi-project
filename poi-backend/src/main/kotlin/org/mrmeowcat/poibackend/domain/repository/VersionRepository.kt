package org.mrmeowcat.poibackend.domain.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.BasicDBObject
import org.mrmeowcat.poibackend.domain.document.AbstractDocument
import org.mrmeowcat.poibackend.domain.document.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository
import kotlin.reflect.KClass

@Repository
class VersionRepository {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    companion object {
        @JvmField val REDUNDANT_FIELDS = arrayOf("createDate", "updateDate", "version", "id")
        @JvmField val VERSION_MAP: Map<KClass<out AbstractDocument>, String> = mapOf(
                Pair(User::class, "users_version")
        )
    }

    fun createVersion(document: AbstractDocument) {
        val collection = VERSION_MAP.getValue(document::class)
        val dbObject = objectMapper.convertValue(document, BasicDBObject::class.java)

        removeRedundantFields(dbObject)

        val obj = BasicDBObject("document", dbObject)
        obj["version"] = document.version
        obj["id"] = document.id
        obj["updateDate"] = document.updateDate
        mongoTemplate.insert(obj, collection)
    }

    private fun removeRedundantFields(document: BasicDBObject) {
        REDUNDANT_FIELDS.forEach { document.removeField(it) }
        document.entries.removeIf { it.value == null }
    }
}