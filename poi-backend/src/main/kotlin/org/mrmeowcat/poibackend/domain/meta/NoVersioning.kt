package org.mrmeowcat.poibackend.domain.meta

/**
 * Marker annotation for fields excluded from document versioning.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class NoVersioning