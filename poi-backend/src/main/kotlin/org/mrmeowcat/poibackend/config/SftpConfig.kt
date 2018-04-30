package org.mrmeowcat.poibackend.config

import org.springframework.context.annotation.Configuration
import java.util.*

/**
 * SFTP Configuration.
 */
@Configuration
class SftpConfig {

    companion object {
        private val bundle = ResourceBundle.getBundle("sftp")
        val HOST = bundle.getString("sftp.host")
        val PORT = bundle.getString("sftp.port").toInt()
        val USERNAME = bundle.getString("sftp.username")
        val PASSWORD = bundle.getString("sftp.password")
        val PUBLIC_URL = bundle.getString("sftp.public.url")
    }

}