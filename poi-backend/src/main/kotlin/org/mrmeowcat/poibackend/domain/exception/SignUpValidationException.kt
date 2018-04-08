package org.mrmeowcat.poibackend.domain.exception

class SignUpValidationException : RuntimeException {

    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, thr: Throwable) : super(message, thr)
}