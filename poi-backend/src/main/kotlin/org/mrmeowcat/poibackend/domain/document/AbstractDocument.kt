package org.mrmeowcat.poibackend.domain.document

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.util.*
import kotlin.reflect.full.memberProperties

abstract class AbstractDocument {

    @Id
    var id: String? = null

    @CreatedDate
    var createDate: Date? = null

    @LastModifiedDate
    var updateDate: Date? = null

    @Version
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
}