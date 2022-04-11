package nd.ermakov.pdris.fileservice

import org.apache.catalina.connector.Connector
import org.apache.catalina.startup.Tomcat
import java.nio.file.Files
import kotlin.io.path.absolutePathString

fun main() {
    val baseDir = Files.createTempDirectory("tomcat").apply { toFile().deleteOnExit() }
    val connector = Connector().apply { port = 9999 }
    val tomcat = Tomcat().apply {
        setBaseDir(baseDir.absolutePathString())
        setConnector(connector)
        host.appBase = "."
        addWebapp("", ".")
    }
    tomcat.start()
    tomcat.server.await()
}
