package de.fhdortmund.schrottverwaltung.user

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(
    val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService{

    fun registerUser(name:String, password:String): User {
    val user = User(null, name, passwordEncoder.encode(password))
    return userRepository.save(user)
}

    override fun loadUserByUsername(username: String): UserDetails? {
        val user = userRepository.getUserByUsername(username)
            ?: throw RuntimeException("User not found with username: $username")

        println(user.username)

        return  org.springframework.security.core.userdetails.User.builder()
            .username(user.username)
            .password(user.password)
            .roles("ADMIN")
            .build()
    }
}

