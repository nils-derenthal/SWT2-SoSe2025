package de.fhdortmund.schrottdaten.immobilie.controller;

import de.fhdortmund.schrottdaten.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottdaten.eigentuemer.repo.EigentuemerRepo;
import de.fhdortmund.schrottdaten.immobilie.EigentumsForm;
import de.fhdortmund.schrottdaten.immobilie.Gebaeudetyp;
import de.fhdortmund.schrottdaten.immobilie.entity.*;
import de.fhdortmund.schrottdaten.immobilie.repo.AdressenRepo;
import de.fhdortmund.schrottdaten.immobilie.repo.ImmobilienRepo;
import de.fhdortmund.schrottdaten.immobilie.repo.KoordinatenRepo;
import de.fhdortmund.schrottdaten.immobilie.service.ImmobilienService;
import de.fhdortmund.schrottdaten.mqtt.MQTTPublisher;
import de.fhdortmund.schrottdaten.mqtt.messages.Action;
import de.fhdortmund.schrottdaten.mqtt.messages.ImmobilienMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/immobilien")
@RequiredArgsConstructor
public class ImmobilienController {
    private final MQTTPublisher publisher;
    private final ImmobilienRepo immobilienRepo;
    private final AdressenRepo adressenRepo;
    private final EigentuemerRepo eigentuemerRepo;
    private final KoordinatenRepo koordinatenRepo;

    private final ResourceLoader resourceLoader;

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
