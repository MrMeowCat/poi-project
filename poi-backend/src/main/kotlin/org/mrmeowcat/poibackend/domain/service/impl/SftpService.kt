package org.mrmeowcat.poibackend.domain.service.impl

import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import org.mrmeowcat.poibackend.config.SftpConfig
import org.mrmeowcat.poibackend.domain.service.FileTransferService
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

/**
 * SFTP download/upload implementation.
 */
@Service
class SftpService : FileTransferService {

    override fun download(path: String): File {

        return File("")
    }

    override fun upload(inputStream: InputStream, filename: String) {
        val properties = Properties()
        properties["StrictHostKeyChecking"] = "no"
        val jsch = JSch()
        val session = jsch.getSession(SftpConfig.USERNAME, SftpConfig.HOST, SftpConfig.PORT)
        session.setPassword(SftpConfig.PASSWORD)
        session.setConfig(properties)
        session.connect()
        val channel = session.openChannel("sftp") as ChannelSftp
        channel.connect()
        channel.put(inputStream as FileInputStream, filename)
        channel.exit()
        session.disconnect()
    }
}