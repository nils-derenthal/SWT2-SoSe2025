package de.fhdortmund.schrottverwaltung.user

import de.fhdortmund.schrottverwaltung.security.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(
    val authenticationManager: AuthenticationManager,
    var jwtService: JwtService,
    val userRepository: UserRepository,
    val encoder: PasswordEncoder,
) {

    fun authenticateUser(user: UserDto): String {
        if (userRepository.existsByUsernameAndPassword(
                user.username,
                encoder.encode(user.password)
            )
        )
            return ""
        return jwtService.generateToken(user.username)
    }

    fun registerUser(user: UserDto): User? {
        if (userRepository.existsByUsername(user.username)) {
            return null
        }
        // Create new user's account
        val newUser = User(
            null,
            user.username,
            encoder.encode(user.password)
        )
        return userRepository.save<User>(newUser)
    }

    fun loadUserByUsername(name: String): User = userRepository.getUserByUsername(name)
}

