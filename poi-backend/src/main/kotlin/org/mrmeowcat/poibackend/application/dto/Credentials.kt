package org.mrmeowcat.poibackend.application.dto

data class Credentials(val username: String = "", val password: String = "") : AbstractRequest()