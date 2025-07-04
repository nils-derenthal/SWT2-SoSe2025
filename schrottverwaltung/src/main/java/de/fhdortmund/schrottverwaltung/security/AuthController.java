package de.fhdortmund.schrottverwaltung.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/validate")
    private boolean validate(){
        // validate if the user is authenticated
        return true;
    }
}
