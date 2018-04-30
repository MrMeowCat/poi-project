package org.mrmeowcat.poibackend.application.mapper

import org.mrmeowcat.poibackend.application.dto.AbstractDto
import org.mrmeowcat.poibackend.domain.document.AbstractDocument
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.starProjectedType

/**
 * Generic bean to dto mapper implementation.
 */
@Component
class BeanMapper : ApplicationContextAware {

    private lateinit var applicationContext: ApplicationContext
    private var mappers = mutableMapOf<KType?, Mapper<AbstractDocument, AbstractDto>>()

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
        val mappers = applicationContext.getBeansOfType(Mapper::class.java).values

        for (mapper in mappers) {
            val type = mapper!!::class.supertypes[0].arguments[0].type
            this.mappers[type] = mapper as Mapper<AbstractDocument, AbstractDto>
        }
    }

    fun<E : AbstractDocument, D : AbstractDto> map(e: E, clazz: KClass<D>) : D {
        val mapper = mappers[e::class.starProjectedType]!!
        return mapper.map(e) as D
    }
}