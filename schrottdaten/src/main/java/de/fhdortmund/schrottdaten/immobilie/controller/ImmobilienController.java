package de.fhdortmund.schrottdaten.immobilie.controller;


import de.fhdortmund.schrottdaten.immobilie.service.ImmobilienService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/immobilien")
@RequiredArgsConstructor
public class ImmobilienController {

    private final ImmobilienService immobilienService;

    @PutMapping("/hinzufuegen")
    public void testMqttImmobilieHinzufuegen() throws IOException {
         immobilienService.dummyHinzufuegen();
    }

    @PutMapping("/update")
    public void testMqttImmobilieUpdaten(){
        immobilienService.dummyUpdate();
    }

    @DeleteMapping("/delete")
    public void testMqttImmobilieLoeschen(){
        immobilienService.dummyDelete();
    }

}
