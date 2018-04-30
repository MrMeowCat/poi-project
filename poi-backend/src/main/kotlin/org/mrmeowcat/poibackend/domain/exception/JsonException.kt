package org.mrmeowcat.poibackend.domain.exception

/**
 * Exception thrown when errors occur while processing json.
 */
class JsonException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, thr: Throwable) : super(message, thr)
}