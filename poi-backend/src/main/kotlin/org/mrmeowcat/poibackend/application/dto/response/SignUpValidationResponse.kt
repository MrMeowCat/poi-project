package org.mrmeowcat.poibackend.application.dto.response

data class SignUpValidationResponse(val status: String,
                                    val errors: List<String>?) : AbstractResponse()