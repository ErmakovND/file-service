package nd.ermakov.pdris.fileservice.config

import nd.ermakov.pdris.fileservice.security.JwtAuthenticationFilter
import nd.ermakov.pdris.fileservice.security.JwtSuccessAuthHandler
import nd.ermakov.pdris.fileservice.service.JwtService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtService: JwtService,
    @Value("\${jwt.cookie}") private val jwtCookie: String
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    @Bean
    fun jwtAuthenticationFilter() = JwtAuthenticationFilter(jwtService, jwtCookie)

    @Bean
    fun successHandler() = JwtSuccessAuthHandler(jwtService, jwtCookie).apply {
        setDefaultTargetUrl("/files")
    }

    @Bean
    fun failureHandler() = SimpleUrlAuthenticationFailureHandler().apply {
        setAllowSessionCreation(false)
    }

    @Bean
    fun authenticationEntryPoint() = Http403ForbiddenEntryPoint()

    override fun configure(http: HttpSecurity) {
        http.csrf()
            .disable()
        http.exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint())
        http.formLogin()
            .loginPage("/login").permitAll()
            .successHandler(successHandler())
            .failureHandler(failureHandler())
        http.authorizeRequests()
            .antMatchers("/", "/actuator/**").permitAll()
            .anyRequest().authenticated()
        http.sessionManagement()
            .sessionCreationPolicy(STATELESS)
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}
