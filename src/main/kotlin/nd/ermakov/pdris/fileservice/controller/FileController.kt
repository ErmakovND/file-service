package nd.ermakov.pdris.fileservice.controller

import nd.ermakov.pdris.fileservice.model.File
import nd.ermakov.pdris.fileservice.service.FileService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/file")
class FileController(private val fileService: FileService) {

    @GetMapping(produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun downloadFile(
        @RequestParam name: String,
        req: HttpServletRequest, res: HttpServletResponse
    ): ByteArray {
        fileService.getFile(name).also {
            res.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + it.name + "\"")
            return fileService.getFile(name).data
        }
    }

    @PutMapping
    fun updateFileName(
        @RequestParam name: String, @RequestParam new: String,
        req: HttpServletRequest, res: HttpServletResponse
    ) {
        fileService.updateFileName(name, new)
        res.sendRedirect("/files")
    }

    @DeleteMapping
    fun deleteFile(
        @RequestParam name: String,
        req: HttpServletRequest, res: HttpServletResponse
    ) {
        fileService.deleteFile(name)
        res.sendRedirect("/files")
    }

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFile(
        @RequestParam name: String, @RequestParam file: MultipartFile,
        req: HttpServletRequest, res: HttpServletResponse
    ) {
        fileService.putFile(File(name = name, data = file.bytes))
        res.sendRedirect("/files")
    }
}
