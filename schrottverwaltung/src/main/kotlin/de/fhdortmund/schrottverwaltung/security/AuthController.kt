package de.fhdortmund.schrottverwaltung.security

import de.fhdortmund.schrottverwaltung.user.UserDto
import de.fhdortmund.schrottverwaltung.user.User
import de.fhdortmund.schrottverwaltung.user.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(

    private val userService: UserService,
) {
    @PostMapping("/login")
    fun authenticateUser(@RequestBody user: UserDto): String = userService.authenticateUser(user)

    @PostMapping("/signup")
    fun registerUser(@RequestBody user: UserDto): User? = userService.registerUser(user)
}