package org.mrmeowcat.poibackend.domain.document

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.mrmeowcat.poibackend.domain.meta.NoVersioning
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.util.*
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * Basic document.
 */
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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
    }

    override fun equals(other: Any?): Boolean {
        val excludedProps = this::class.memberProperties
                .filter { it.findAnnotation<NoVersioning>() != null }
                .map { it.name }
        return EqualsBuilder.reflectionEquals(this, other, excludedProps)
    }

    override fun hashCode(): Int {
        val excludedProps = this::class.memberProperties
                .filter { it.findAnnotation<NoVersioning>() != null }
                .map { it.name }
        return HashCodeBuilder.reflectionHashCode(this, excludedProps)
    }
}
