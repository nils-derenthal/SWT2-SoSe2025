package de.fhdortmund.schrottverwaltung.security;

import de.fhdortmund.schrottverwaltung.mitarbeiter.MitarbeiterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final MitarbeiterService mitarbeiterService;

    @PostMapping("/login")
    private void login(@RequestBody AuthDto credentials){
        log.info("Login: {} {}",credentials.username(), credentials.password());
    }

    @PostMapping("/register")
    private void register(@RequestBody CreateUserDto credentials){
        log.info("Register: {} {}",credentials.email(), credentials.password());
        mitarbeiterService.register(credentials);
    }
}
