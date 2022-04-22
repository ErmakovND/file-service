package nd.ermakov.pdris.fileservice.config

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class AppInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {
    override fun getServletMappings() = arrayOf("/*")

    override fun getRootConfigClasses() = arrayOf(WebConfig::class.java)

    override fun getServletConfigClasses() = null
}
