package org.mrmeowcat.poibackend.application.dto

data class SignUpRequest(val username: String?,
                         val email: String?,
                         val password: String?,
                         val confirm: String?) : AbstractRequest()