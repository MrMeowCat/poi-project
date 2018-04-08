package org.mrmeowcat.poibackend.application.handler

import org.mrmeowcat.poibackend.application.dto.MessageResponse
import org.mrmeowcat.poibackend.domain.exception.SignUpValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(SignUpValidationException::class)
    fun handleSignUp(e: SignUpValidationException) : ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageResponse(e.message))
    }

}