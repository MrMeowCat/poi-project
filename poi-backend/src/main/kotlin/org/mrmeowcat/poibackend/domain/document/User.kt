package org.mrmeowcat.poibackend.domain.document

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
class User : AbstractDocument() {

    @Indexed(unique = true)
    lateinit var name: String

    @Indexed(unique = true)
    lateinit var email: String

    lateinit var password: String

    lateinit var roles: List<String>

    var enabled = true


}