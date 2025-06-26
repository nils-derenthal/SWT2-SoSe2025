package de.fhdortmund.schrottverwaltung.security

import de.fhdortmund.schrottverwaltung.user.UserService
import lombok.AllArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
@AllArgsConstructor
class SecurityConfiguration(val userService: UserService) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .cors { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/user/register", "/hello/world")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .httpBasic { }

        println("initialized filterchain")
        return http.build()
    }

    @Bean
    fun authenticationProvider(passwordEncoder: PasswordEncoder): DaoAuthenticationProvider {
        val provider =  DaoAuthenticationProvider(passwordEncoder)
        provider.setUserDetailsService(userService)
        println("initialized auth provider")
        return provider
    }

    @Bean
    fun authenticationManager(passwordEncoder: PasswordEncoder): AuthenticationManager {
        val provider =  DaoAuthenticationProvider(passwordEncoder)
        provider.setUserDetailsService(userService)
        println("initialized auth manager")
        return ProviderManager(provider)
    }
}
