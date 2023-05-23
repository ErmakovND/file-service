package nd.ermakov.pdris.fileservice.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.Table

@Entity
class File(
    @Id @GeneratedValue var id: Long? = null,
    val name: String,
    @Lob val data: ByteArray
)
