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
        try {
            println("extract jwt")
            val jwt = parseJwt(request)
            println("jwt: $jwt")
            val valid = jwt != null && jwtService.validateJwtToken(jwt)
            println(if (valid) "valid" else "invalid")
            if (!valid)
                throw Exception("du bist behindert")

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