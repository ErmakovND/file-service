package nd.ermakov.pdris.fileservice.service

import nd.ermakov.pdris.fileservice.model.User
import nd.ermakov.pdris.fileservice.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun getByName(name: String): User? {
        return userRepository.findByName(name)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return getByName(username)?.let {
            org.springframework.security.core.userdetails.User(it.name, it.password, emptyList())
        } ?: throw UsernameNotFoundException("User not exists")
    }
}
