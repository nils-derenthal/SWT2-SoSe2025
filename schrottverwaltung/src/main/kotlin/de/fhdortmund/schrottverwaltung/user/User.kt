package de.fhdortmund.schrottverwaltung.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.hibernate.internal.util.collections.CollectionHelper.setOf
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id:Long?,
    @Column(unique = true)
    private val username: String,
    private val password: String,
) : UserDetails {
    override fun  getAuthorities(): MutableCollection<GrantedAuthority> = setOf()

    override fun getPassword(): String = password

    override fun getUsername(): String = username
}