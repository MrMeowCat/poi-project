package org.mrmeowcat.poibackend.application.mapper

import org.mrmeowcat.poibackend.application.dto.ThemeDto
import org.mrmeowcat.poibackend.domain.document.Theme
import org.springframework.stereotype.Component

/**
 * Mapper for theme documents.
 */
@Component
class Theme2ThemeDtoMapper : Mapper<Theme, ThemeDto> {

    override fun map(e: Theme): ThemeDto {
        val name = e.name!!
        val style = e.style!!
        val dto = ThemeDto(name, style)
        dto.id = e.id
        return dto
    }
}