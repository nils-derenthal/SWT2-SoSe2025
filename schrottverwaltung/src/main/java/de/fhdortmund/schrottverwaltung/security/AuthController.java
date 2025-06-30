package de.fhdortmund.schrottverwaltung.security;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    private void login(@RequestBody AuthDto credentials){
        System.out.println("Login: " + credentials.username() +" "+ credentials.password());
    }

    @PostMapping("/register")
    private void register(@RequestBody AuthDto credentials){
        System.out.println("Register: " + credentials.username() +" "+ credentials.password());
    }
}
