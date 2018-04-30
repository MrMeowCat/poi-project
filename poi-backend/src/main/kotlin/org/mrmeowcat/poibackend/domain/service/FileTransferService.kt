package org.mrmeowcat.poibackend.domain.service

import java.io.File
import java.io.InputStream

/**
 * Service for file download/upload.
 */
interface FileTransferService {

    fun download(path: String) : File

    fun upload(inputStream: InputStream, filename: String)
}