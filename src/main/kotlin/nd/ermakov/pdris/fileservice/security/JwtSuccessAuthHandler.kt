package nd.ermakov.pdris.fileservice.security

import nd.ermakov.pdris.fileservice.service.JwtService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtSuccessAuthHandler(
    private val jwtService: JwtService,
    private val jwtCookie: String
) : SimpleUrlAuthenticationSuccessHandler() {
    override fun handle(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        response.addCookie(Cookie(jwtCookie, jwtService.createToken(authentication.name)))
        super.handle(request, response, authentication)
    }
}
