package org.mrmeowcat.poibackend.application.dto.request

data class Credentials(val username: String = "",
                       val password: String = "",
                       val rememberMe: Boolean) : AbstractRequest()