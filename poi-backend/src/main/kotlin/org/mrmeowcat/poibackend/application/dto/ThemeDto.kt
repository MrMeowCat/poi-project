package org.mrmeowcat.poibackend.application.dto

/**
 *  Theme DTO.
 */
class ThemeDto(id: String? = null) : AbstractDto(id) {

    var name: String = ""
    var style: String = ""
    var mapVendor: String = ""

    constructor(name: String, style: String, mapVendor: String) : this() {
        this.name = name
        this.style = style
        this.mapVendor = mapVendor
    }

    constructor(id: String, name: String, style: String, mapVendor: String)
            : this(id) {
        this.id = id
        this.name = name
        this.style = style
        this.mapVendor = mapVendor
    }
}