package nd.ermakov.pdris.fileservice.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
class User(
    @Id @GeneratedValue var id: Long? = null,
    var name: String,
    var password: String
)
