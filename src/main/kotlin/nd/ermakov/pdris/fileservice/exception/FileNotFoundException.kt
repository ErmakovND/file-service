package nd.ermakov.pdris.fileservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "File not found")
class FileNotFoundException : RuntimeException()
