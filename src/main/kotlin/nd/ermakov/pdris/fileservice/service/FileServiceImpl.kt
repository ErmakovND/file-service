package nd.ermakov.pdris.fileservice.service

import nd.ermakov.pdris.fileservice.exception.FileAlreadyExistsException
import nd.ermakov.pdris.fileservice.exception.FileNotFoundException
import nd.ermakov.pdris.fileservice.model.File
import nd.ermakov.pdris.fileservice.repository.FileRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class FileServiceImpl(private val fileRepository: FileRepository) : FileService {

    override fun putFile(file: File) {
        if (fileRepository.existsByName(file.name)) {
            throw FileAlreadyExistsException()
        }
        fileRepository.save(file)
    }

    override fun getFile(name: String): File {
        return fileRepository.findByName(name) ?: throw FileNotFoundException()
    }

    override fun getFileNames(): List<String> {
        return fileRepository.findAllNames()
    }

    override fun updateFileName(oldName: String, newName: String) {
        fileRepository.updateName(oldName, newName)
    }

    override fun deleteFile(name: String) {
        fileRepository.removeByName(name)
    }

}
