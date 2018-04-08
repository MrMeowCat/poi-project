package org.mrmeowcat.poibackend.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@ConfigurationProperties
@PropertySource("classpath:app_config.yaml")
class AppConfig {

    lateinit var domain: String
    lateinit var emailPattern: String
    lateinit var passwordPattern: String
}