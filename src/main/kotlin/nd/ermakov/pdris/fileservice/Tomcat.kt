package nd.ermakov.pdris.fileservice

import org.apache.catalina.startup.Tomcat
import java.io.File
import java.nio.file.Files
import kotlin.io.path.absolutePathString

fun main() {
    val tomcat = Tomcat().apply {
        Files.createTempDirectory("tomcat")
            .apply { toFile().deleteOnExit() }
            .let { setBaseDir(it.absolutePathString()) }
        setPort(9999)
        addWebapp("", File("src/main/").absolutePath)
    }
    tomcat.start()
    tomcat.server.await()
}
