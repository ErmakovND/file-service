package nd.ermakov.pdris.fileservice.config

import org.apache.naming.SelectorContext.prefix
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver

@Configuration
@EnableWebMvc
@ComponentScan("nd.ermakov.pdris.fileservice")
open class WebConfig : WebMvcConfigurer {
    @Bean("multipartResolver")
    open fun multipartResolver() = CommonsMultipartResolver()

    @Bean
    open fun templateResolver() = SpringResourceTemplateResolver().apply {
        prefix = "resources/views/"
        suffix = ".html"
    }

    @Bean
    open fun templateEngine(resolver: SpringResourceTemplateResolver) = SpringTemplateEngine().apply {
        setTemplateResolver(resolver)
    }

    @Bean
    open fun viewResolver(engine: SpringTemplateEngine) = ThymeleafViewResolver().apply {
        templateEngine = engine
    }

    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }
}
