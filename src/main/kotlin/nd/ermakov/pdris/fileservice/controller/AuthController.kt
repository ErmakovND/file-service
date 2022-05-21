package nd.ermakov.pdris.fileservice.controller

import nd.ermakov.pdris.fileservice.filter.SESSION_ATTRIBUTE_USERNAME
import nd.ermakov.pdris.fileservice.service.UserService
import org.eclipse.jdt.internal.compiler.parser.Parser.name
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/login")
class AuthController(private val userService: UserService) {
    @GetMapping
    fun getLoginPage() = ModelAndView("login")

    @PostMapping
    fun loginUser(
        @RequestParam name: String, @RequestParam password: String,
        req: HttpServletRequest, res: HttpServletResponse
    ) {
        if (userService.getByName(name)?.password == password) {
            req.session.setAttribute(SESSION_ATTRIBUTE_USERNAME, name)
            res.sendRedirect("/files")
        } else {
            req.session.removeAttribute(SESSION_ATTRIBUTE_USERNAME)
            res.sendRedirect("/login")
        }
    }
}
