package nd.ermakov.pdris.fileservice.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expiration}") private val expiration: Int
) {

    private val algorithm = Algorithm.HMAC256(secret)

    fun createToken(username: String): String {
        return JWT.create()
            .withSubject(username)
            .withExpiresAt(Date(System.currentTimeMillis() + expiration * 1000))
            .sign(algorithm)
    }

    fun verifyToken(token: String): Boolean {
        try {
            JWT.require(algorithm).build().verify(token)
        } catch (e: JWTVerificationException) {
            return false
        }
        return true
    }

    fun decodeToken(token: String): DecodedJWT {
        return JWT.decode(token)
    }
}
