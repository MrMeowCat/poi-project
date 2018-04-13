package org.mrmeowcat.poibackend.application.dto.request

import org.mrmeowcat.poibackend.application.dto.request.AbstractRequest

data class SignUpRequest(val username: String?,
                         val email: String?,
                         val password: String?,
                         val confirm: String?) : AbstractRequest()