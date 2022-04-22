package nd.ermakov.pdris.fileservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "File already exists")
class FileAlreadyExistsException : RuntimeException()
