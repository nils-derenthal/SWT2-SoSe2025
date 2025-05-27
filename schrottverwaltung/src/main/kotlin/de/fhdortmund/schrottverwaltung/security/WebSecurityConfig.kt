package de.fhdortmund.schrottverwaltung.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


    @Configuration
    @EnableWebSecurity
    class WebSecurityConfig {
        @Bean
        fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
            http
                .csrf { it.disable() }
                .cors { it.disable() }
                .httpBasic{ it.disable() }
            return http.build()
        }
    }
