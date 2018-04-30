package org.mrmeowcat.poibackend.application.dto.request

/**
 * User credentials.
 */
data class Credentials(val username: String = "",
                       val password: String = "") : AbstractRequest()