package nd.ermakov.pdris.fileservice.config

import nd.ermakov.pdris.fileservice.filter.AuthFilter
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
import javax.servlet.DispatcherType
import javax.servlet.Filter
import javax.servlet.FilterRegistration
import javax.servlet.ServletContext

class AppInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {
    override fun getServletMappings() = arrayOf("/*")

    override fun getRootConfigClasses() = arrayOf(
        WebConfig::class.java,
        JdbcConfig::class.java
    )

    override fun getServletConfigClasses() = null

    override fun getServletFilters() = arrayOf(AuthFilter())
}
