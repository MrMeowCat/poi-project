package org.mrmeowcat.poibackend

import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import org.junit.Test
import org.mrmeowcat.poibackend.config.SftpConfig
import java.io.File
import java.io.FileInputStream
import java.util.*


class SftpTests {



    @Test
    fun test() {
        val file = File("C:\\Users\\Vitek\\Desktop\\linux\\2\\2-2.png")

        val properties = Properties()
        properties["StrictHostKeyChecking"] = "no"
        val jsch = JSch()
        val session = jsch.getSession(SftpConfig.USERNAME, SftpConfig.HOST, SftpConfig.PORT)
        session.setPassword(SftpConfig.PASSWORD)
        session.setConfig(properties)
        session.connect()
        val channel = session.openChannel("sftp") as ChannelSftp
        channel.connect()
//        channel.mkdir("avatar")
        channel.put(FileInputStream(file), file.name)

        channel.exit()
        session.disconnect()


    }
}