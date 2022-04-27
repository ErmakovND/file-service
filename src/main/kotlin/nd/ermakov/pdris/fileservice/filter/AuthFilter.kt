package nd.ermakov.pdris.fileservice.filter

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.FilterChain
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

const val SESSION_ATTRIBUTE_USERNAME = "username"
val PUBLIC_URLS = listOf("/", "/login")

@Component
class AuthFilter : HttpFilter() {
    override fun doFilter(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        if (!isPublicUrl(req.pathInfo) && req.session.getAttribute(SESSION_ATTRIBUTE_USERNAME) == null) {
            res.status = HttpStatus.FORBIDDEN.value()
            return
        }
        chain.doFilter(req, res)
    }

    private fun isPublicUrl(path: String) = path in PUBLIC_URLS
}
