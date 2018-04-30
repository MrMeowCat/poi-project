package org.mrmeowcat.poibackend.domain.service

import org.mrmeowcat.poibackend.application.dto.request.SignUpRequest
import org.mrmeowcat.poibackend.application.dto.response.SignUpValidationResponse

/**
 * Service for sign up handling.
 */
interface SignUpService {

    fun signUp(signUpRequest: SignUpRequest?) : SignUpValidationResponse

}