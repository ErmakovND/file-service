package nd.ermakov.pdris.fileservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
@ComponentScan("nd.ermakov.pdris.fileservice")
open class WebConfig : WebMvcConfigurer {
    override fun configureHandlerExceptionResolvers(resolvers: MutableList<HandlerExceptionResolver>) {
        super.configureHandlerExceptionResolvers(resolvers)
    }

    @Bean("multipartResolver")
    open fun multipartResolver() = CommonsMultipartResolver()
}
