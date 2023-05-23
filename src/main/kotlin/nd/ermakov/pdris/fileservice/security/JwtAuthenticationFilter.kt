package nd.ermakov.pdris.fileservice.security

import nd.ermakov.pdris.fileservice.service.JwtService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val jwtCookie: String
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        request.cookies?.find { it.name == jwtCookie }?.value?.also { jwt ->
            if (jwtService.verifyToken(jwt)) {
                jwtService.decodeToken(jwt).subject
                    .let { UsernamePasswordAuthenticationToken.authenticated(it, null, emptyList()) }
                    .also { SecurityContextHolder.getContext().authentication = it }
            }
        }
        filterChain.doFilter(request, response)
    }
}
