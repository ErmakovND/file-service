package nd.ermakov.pdris.fileservice.service

import nd.ermakov.pdris.fileservice.model.File

interface FileService {
    fun putFile(file: File)
    fun getFile(name: String): File
    fun getFileNames(): List<String>
    fun updateFileName(oldName: String, newName: String)
    fun deleteFile(name: String)
}
