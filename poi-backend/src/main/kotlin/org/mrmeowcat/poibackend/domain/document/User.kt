package org.mrmeowcat.poibackend.domain.document

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
class User : AbstractDocument() {

    @Indexed(unique = true)
    var username: String? = null

    @Indexed(unique = true)
    var email: String? = null

    var password: String? = null

    var roles: List<String> = mutableListOf()

    var enabled = true

    var language: String? = null
}