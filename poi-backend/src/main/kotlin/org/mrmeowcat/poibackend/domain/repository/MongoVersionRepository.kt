package org.mrmeowcat.poibackend.domain.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.BasicDBObject
import org.bson.Document
import org.mrmeowcat.poibackend.domain.document.AbstractDocument
import org.mrmeowcat.poibackend.domain.document.Theme
import org.mrmeowcat.poibackend.domain.document.User
import org.mrmeowcat.poibackend.domain.meta.NoVersioning
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * Generic versioning repository for mongo documents.
 */
@Repository
class MongoVersionRepository {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    companion object {
        @JvmField val VERSION_MAP: Map<KClass<out AbstractDocument>, String> = mapOf(
                Pair(User::class, "users_version"),
                Pair(Theme::class, "themes_version")
        )
    }

    fun <T : AbstractDocument> previousVersion(document: T) : T? {
        val collection = VERSION_MAP.getValue(document::class)
        val query = Query()
        query.addCriteria(Criteria.where("id").`is`(document.id))
                .with(Sort(Sort.Direction.DESC, "version"))

        val previous = mongoTemplate.findOne(query, Document::class.java, collection)
        previous ?: return null
        return objectMapper.convertValue(previous["document"], document.javaClass)
    }

    fun createVersion(document: AbstractDocument) {
        val collection = VERSION_MAP.getValue(document::class)
        val dbObject = objectMapper.convertValue(document, BasicDBObject::class.java)
        val excludedFields = getExcludedFields(document)

        /*Remove fields marked as @NoVersioning and null fields*/
        excludedFields.forEach { dbObject.removeField(it) }
        dbObject.entries.removeIf { it.value == null }

        val obj = BasicDBObject("document", dbObject)
        obj["version"] = document.version
        obj["id"] = document.id
        obj["updateDate"] = document.updateDate
        mongoTemplate.insert(obj, collection)
    }

    private fun getExcludedFields(document: AbstractDocument) : List<String> {
        val props = document::class.memberProperties
        val fields = mutableListOf<String>()
        for (prop in props) {
            prop.findAnnotation<NoVersioning>() ?: continue
            fields.add(prop.name)
        }
        return fields
    }
}