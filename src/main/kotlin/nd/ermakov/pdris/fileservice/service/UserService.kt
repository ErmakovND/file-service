package nd.ermakov.pdris.fileservice.service

import nd.ermakov.pdris.fileservice.model.User

interface UserService {
    fun getByName(name: String): User?
}
