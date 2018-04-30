package org.mrmeowcat.poibackend.application.handler

import org.mrmeowcat.poibackend.domain.exception.DocumentNotFoundException
import org.mrmeowcat.poibackend.domain.exception.JsonException
import org.mrmeowcat.poibackend.domain.exception.SignUpValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Application exception handler.
 */
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(SignUpValidationException::class)
    fun handleSignUp(e: SignUpValidationException) : ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.response)
    }

    @ExceptionHandler(JsonException::class)
    fun handleJsonException(e: JsonException) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
    }

    @ExceptionHandler(DocumentNotFoundException::class)
    fun handleJsonException(e: DocumentNotFoundException) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
}