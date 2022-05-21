package nd.ermakov.pdris.fileservice.controller

import nd.ermakov.pdris.fileservice.service.FileService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping
class PageController(
    private val fileService: FileService
) {
    @GetMapping
    fun getHomePage() = ModelAndView("index")

    @GetMapping("/login")
    fun getLoginPage() = ModelAndView("login")

    @GetMapping("/files")
    fun getFiles() = ModelAndView("files").apply {
        addObject("files", fileService.getFileNames())
    }
}
