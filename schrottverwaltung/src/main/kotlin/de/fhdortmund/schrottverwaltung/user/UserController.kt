package de.fhdortmund.schrottverwaltung.user

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {
    @PostMapping("/register")
    fun register(@RequestBody user: UserDto): User = userService.registerUser(user.username, user.password)
}