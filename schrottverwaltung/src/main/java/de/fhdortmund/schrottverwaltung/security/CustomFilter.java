package de.fhdortmund.schrottverwaltung.security;

import de.fhdortmund.schrottverwaltung.mitarbeiter.MitarbeiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomFilter extends OncePerRequestFilter {
    private final MitarbeiterService mitarbeiterService;
    private final Encoder encoder;
    public String allowedRoute = "/user/register";

    @Override
    public void doFilterInternal(@NonNull HttpServletRequest servletRequest, @NonNull HttpServletResponse servletResponse, @NonNull FilterChain filterChain) throws IOException, ServletException {
        // Filter out allowed route
        if(String.valueOf(servletRequest.getRequestURL()).endsWith(allowedRoute)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String header = servletRequest.getHeader("Authorization");
        if(header == null || !header.startsWith("Basic ")) {
            log.error("Authorization header is missing");
            servletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // Get Username and Password from Authorization Header
        String rawValue = header.substring(6);
        String decodedValue = new String(Base64.getDecoder().decode(rawValue), StandardCharsets.UTF_8);
        String[] split = decodedValue.split(":");
        String username = split[0];
        String password = split[1];
        String encodedPassword = encoder.encrypt(password);
        UserDetails user = mitarbeiterService.loadUserByUsername(username);

        if (user == null) {
            log.error("User not found");
            servletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        if(!user.getPassword().equals(encodedPassword)) {
            log.error("Password incorrect");
            servletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        log.info("Authenticated");

        updateSecurityContext(servletRequest, user);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private void updateSecurityContext(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
        auth.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
