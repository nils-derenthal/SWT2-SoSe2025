package de.fhdortmund.schrottdaten.eigentuemer.controller;

import de.fhdortmund.schrottdaten.eigentuemer.service.EigentuemerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eigentuemer")
@RequiredArgsConstructor
public class EigentuemerController {
    private final EigentuemerService eigentuemerService;

    @PutMapping("/hinzufuegen")
    public void testMqttEigentuemerHinzufuegen(){
        eigentuemerService.dummyEinfuegen();
    }

    @PutMapping("/update")
    public void testMqttEigentuemerUpdaten(){
        eigentuemerService.dummyUpdate();
    }
}
