package nd.ermakov.pdris.fileservice.repository

import nd.ermakov.pdris.fileservice.model.File
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class InMemoryFileRepository : FileRepository {
    private val storage = ConcurrentHashMap<String, File>()

    override fun putFile(file: File) {
        storage[file.name] = file
    }

    override fun getFile(name: String): File? {
        return storage[name]
    }

    override fun hasFile(name: String): Boolean {
        return storage.containsKey(name)
    }

    override fun getFileNames(): List<String> {
        return storage.keys().toList()
    }
}
