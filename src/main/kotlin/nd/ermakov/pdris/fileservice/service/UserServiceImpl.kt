package nd.ermakov.pdris.fileservice.service

import nd.ermakov.pdris.fileservice.model.User
import nd.ermakov.pdris.fileservice.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun getByName(name: String): User? {
        return userRepository.findByName(name)
    }
}
