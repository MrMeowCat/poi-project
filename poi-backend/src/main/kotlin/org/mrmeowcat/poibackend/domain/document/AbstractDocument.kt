package org.mrmeowcat.poibackend.domain.document

import org.mrmeowcat.poibackend.domain.meta.NoVersioning
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.util.*
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

abstract class AbstractDocument {

    @Id
    @NoVersioning
    var id: String? = null

    @CreatedDate
    @NoVersioning
    var createDate: Date? = null

    @LastModifiedDate
    @NoVersioning
    var updateDate: Date? = null

    @Version
    @NoVersioning
    var version = 0

    override fun toString(): String {
        val props = this::class.memberProperties
        val sb = StringBuilder("${this::class.simpleName}(")
        for ((index, prop) in props.withIndex()) {
            sb.append("${prop.name}=${prop.call(this)}, ")
            if (index == props.size - 1) {
                sb.delete(sb.length - 2, sb.length)
            }
        }

        return sb.append(")").toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        val props = this::class.memberProperties
        for (prop in props) {
            if (prop.findAnnotation<NoVersioning>() != null) continue
            if (prop.call(this) != prop.call(other)) return false
        }

        return true
    }

    override fun hashCode(): Int {
        val props = this::class.memberProperties
        val values = mutableListOf<Any?>()
        for (prop in props) {
            if (prop.findAnnotation<NoVersioning>() != null) continue
            values.add(prop.call(this))
        }

        return Objects.hash(values)
    }
}
