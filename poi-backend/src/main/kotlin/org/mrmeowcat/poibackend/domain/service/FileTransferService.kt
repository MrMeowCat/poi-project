package org.mrmeowcat.poibackend.domain.service

import java.io.File
import java.io.InputStream

interface FileTransferService {

    fun download(path: String) : File

    fun upload(inputStream: InputStream, filename: String)
}