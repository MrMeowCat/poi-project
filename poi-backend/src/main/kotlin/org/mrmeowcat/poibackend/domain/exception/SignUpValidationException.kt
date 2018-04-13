package org.mrmeowcat.poibackend.domain.exception

import org.mrmeowcat.poibackend.application.dto.response.SignUpValidationResponse

class SignUpValidationException(val response: SignUpValidationResponse) : RuntimeException()