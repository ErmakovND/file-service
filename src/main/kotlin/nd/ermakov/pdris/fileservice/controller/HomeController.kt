package nd.ermakov.pdris.fileservice.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping
class HomeController {
    @GetMapping
    fun getHomePage() = ModelAndView("index")
}
