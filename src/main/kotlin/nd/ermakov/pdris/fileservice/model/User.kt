package nd.ermakov.pdris.fileservice.model

import org.springframework.data.annotation.Id

data class User(
    @Id var id: Int? = null,
    var name: String,
    var password: String
)
