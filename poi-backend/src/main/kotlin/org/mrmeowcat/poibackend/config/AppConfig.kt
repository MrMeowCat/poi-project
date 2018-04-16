package org.mrmeowcat.poibackend.config

import java.util.*

object AppConfig {

    private val bundle = ResourceBundle.getBundle("app_config")
    val DOMAIN = bundle.getString("application.domain")
    val EMAIL_PATTERN = bundle.getString("validation.emailPattern")
    val PASSWORD_PATTERN = bundle.getString("validation.passwordPattern")
}