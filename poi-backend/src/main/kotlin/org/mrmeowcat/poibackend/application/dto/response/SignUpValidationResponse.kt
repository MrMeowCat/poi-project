package org.mrmeowcat.poibackend.application.dto.response

/**
 * Results of Sign Up.
 */
data class SignUpValidationResponse(val status: String,
                                    val errors: List<String>?) : AbstractResponse()