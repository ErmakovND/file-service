package nd.ermakov.pdris.fileservice.controller

import nd.ermakov.pdris.fileservice.model.File
import nd.ermakov.pdris.fileservice.service.FileService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/files")
class FileController(private val fileService: FileService) {

    @GetMapping(produces = [MediaType.TEXT_HTML_VALUE])
    fun getFiles() = ModelAndView("files").apply {
        addObject("files", fileService.getFileNames())
    }

    @GetMapping("/download", produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun downloadFile(@RequestParam name: String): ByteArray {
        return fileService.getFile(name).data
    }

    @GetMapping("/upload", produces = [MediaType.TEXT_HTML_VALUE])
    fun getUploadPage() = ModelAndView("upload")

    @PostMapping("/upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFile(
        @RequestParam name: String, @RequestParam file: MultipartFile,
        req: HttpServletRequest, res: HttpServletResponse
    ) {
        fileService.putFile(File(name, file.bytes))
        res.sendRedirect("/files/upload")
    }
}
