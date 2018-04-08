package org.mrmeowcat.poibackend.domain.service

import org.mrmeowcat.poibackend.application.dto.SignUpRequest

interface SignUpService {

    fun signUp(signUpRequest: SignUpRequest?)

}