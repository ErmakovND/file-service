package nd.ermakov.pdris.fileservice.repository

import nd.ermakov.pdris.fileservice.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByName(name: String): User?
}
