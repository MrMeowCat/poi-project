package org.mrmeowcat.poibackend.application.mapper

import org.mrmeowcat.poibackend.application.dto.AbstractDto
import org.mrmeowcat.poibackend.domain.document.AbstractDocument

internal interface Mapper<in E : AbstractDocument, out D : AbstractDto> {

    fun map(e: E) : D
}