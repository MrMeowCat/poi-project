package org.mrmeowcat.poibackend.domain.service

import org.mrmeowcat.poibackend.domain.document.Theme

/**
 * Theme CRUD service.
 */
interface ThemeService : DBService<Theme> {

    fun findByName(name: String) : List<Theme>

    fun findByUserId(id: String) : List<Theme>

    fun existsByName(name: String) : Boolean
}