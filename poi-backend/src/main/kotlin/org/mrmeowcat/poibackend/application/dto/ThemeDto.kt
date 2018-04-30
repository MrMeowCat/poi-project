package org.mrmeowcat.poibackend.application.dto

/**
 *  Theme DTO.
 */
class ThemeDto(id: String? = null) : AbstractDto(id) {

    var name: String = ""
    var style: String = ""

    constructor(name: String, style: String) : this() {
        this.name = name
        this.style = style
    }

    constructor(id: String, name: String, style: String) : this(id) {
        this.id = id
        this.name = name
        this.style = style
    }
}