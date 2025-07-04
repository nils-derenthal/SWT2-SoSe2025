package de.fhdortmund.schrottverwaltung.mitarbeiter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MitarbeiterController {
    private final MitarbeiterService mitarbeiterService;

    @PostMapping("/register")
    private void register(@RequestBody CreateUserDto credentials){
        mitarbeiterService.register(credentials);
    }
}
