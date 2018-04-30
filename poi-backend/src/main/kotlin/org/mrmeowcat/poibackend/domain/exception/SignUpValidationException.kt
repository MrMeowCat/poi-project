package org.mrmeowcat.poibackend.domain.exception

import org.mrmeowcat.poibackend.application.dto.response.SignUpValidationResponse

/**
 * Exception thrown when sign up fails.
 */
class SignUpValidationException(val response: SignUpValidationResponse) : RuntimeException()