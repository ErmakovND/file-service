package nd.ermakov.pdris.fileservice.controller

import nd.ermakov.pdris.fileservice.model.File
import nd.ermakov.pdris.fileservice.service.FileService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/file")
class FileController(private val fileService: FileService) {

    @GetMapping
    fun getFile(@RequestParam filename: String): ByteArray {
        return fileService.getFile(filename).data
    }

    @PostMapping
    fun putFile(@RequestParam file: MultipartFile) {
        fileService.putFile(File(file.name, file.bytes))
    }
}
