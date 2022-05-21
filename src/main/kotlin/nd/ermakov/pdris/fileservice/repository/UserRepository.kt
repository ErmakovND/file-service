package nd.ermakov.pdris.fileservice.repository

import nd.ermakov.pdris.fileservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User?
}
