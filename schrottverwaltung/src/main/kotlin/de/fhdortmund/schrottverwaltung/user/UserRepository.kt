package de.fhdortmund.schrottverwaltung.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun existsByUsername(username: String) : Boolean
    fun getUserByUsername(username: String) : User?

    fun existsByUsernameAndPassword(username: String, password: String) : Boolean
}