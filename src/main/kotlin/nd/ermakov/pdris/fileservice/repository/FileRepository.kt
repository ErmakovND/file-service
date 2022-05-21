package nd.ermakov.pdris.fileservice.repository

import nd.ermakov.pdris.fileservice.model.File

interface FileRepository {
    fun putFile(file: File)
    fun getFile(name: String): File?
    fun hasFile(name: String): Boolean
    fun getFileNames(): List<String>
}
