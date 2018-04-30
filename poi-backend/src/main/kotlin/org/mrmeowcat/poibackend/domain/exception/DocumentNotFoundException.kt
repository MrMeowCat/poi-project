package org.mrmeowcat.poibackend.domain.exception

/**
 * Exception thrown when document is not found in database.
 */
class DocumentNotFoundException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, thr: Throwable) : super(message, thr)
}