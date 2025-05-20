package de.fhdortmund.schrottverwaltung.security

import de.fhdortmund.schrottverwaltung.user.User
import de.fhdortmund.schrottverwaltung.user.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthTokenFilter(

) : OncePerRequestFilter() {
    @Autowired
    lateinit var jwtService: JwtService

    @Autowired
    lateinit var userService: UserService
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("doFilterInternal")
        try {
            val jwt = parseJwt(request)
            if (jwt != null && jwtService.validateJwtToken(jwt)) {
                val username: String = jwtService.getUsernameFromToken(jwt)
                val userDetails: User? = userService.loadUserByUsername(username)
                println("rofl")
                val authentication =
                    UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails?.authorities
                    )
                println("kekw")
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            println("Cannot set user authentication: $e")
        }
        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader("Authorization")
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7)
        }
        return null
    }
}