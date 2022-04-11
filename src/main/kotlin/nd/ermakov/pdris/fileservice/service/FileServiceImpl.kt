package nd.ermakov.pdris.fileservice.service

import nd.ermakov.pdris.fileservice.exception.FileAlreadyExistsException
import nd.ermakov.pdris.fileservice.exception.FileNotFoundException
import nd.ermakov.pdris.fileservice.model.File
import nd.ermakov.pdris.fileservice.repository.FileRepository
import org.springframework.stereotype.Service

@Service
class FileServiceImpl(private val fileRepository: FileRepository) : FileService {

    override fun putFile(file: File) {
        if (fileRepository.hasFile(file.name)) {
            throw FileAlreadyExistsException()
        }
        fileRepository.putFile(file)
    }

    override fun getFile(name: String): File {
        return fileRepository.getFile(name) ?: throw FileNotFoundException()
    }

}
