package org.mrmeowcat.poibackend.domain.document

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import java.util.*

abstract class AbstractDocument {

    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createDate: Date

    @LastModifiedDate
    lateinit var updateDate: Date
}