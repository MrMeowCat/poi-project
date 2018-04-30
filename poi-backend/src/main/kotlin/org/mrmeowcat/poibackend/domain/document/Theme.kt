package org.mrmeowcat.poibackend.domain.document

import org.mrmeowcat.poibackend.domain.meta.NoVersioning
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Theme document.
 */
@Document(collection = "themes")
class Theme : AbstractDocument() {

    @Indexed
    var name: String? = null

    var style: String? = null

    @NoVersioning
    var userId: String? = null

}
