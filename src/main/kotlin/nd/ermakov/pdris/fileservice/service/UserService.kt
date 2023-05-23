package nd.ermakov.pdris.fileservice.service

import nd.ermakov.pdris.fileservice.model.User
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : UserDetailsService {
    fun getByName(name: String): User?
}
