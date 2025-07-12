package de.fhdortmund.schrottdaten.eigentuemer.controller;

import de.fhdortmund.schrottdaten.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottdaten.eigentuemer.repo.EigentuemerRepo;
import de.fhdortmund.schrottdaten.immobilie.entity.Adresse;
import de.fhdortmund.schrottdaten.immobilie.repo.AdressenRepo;
import de.fhdortmund.schrottdaten.mqtt.MQTTPublisher;
import de.fhdortmund.schrottdaten.mqtt.messages.Action;
import de.fhdortmund.schrottdaten.mqtt.messages.EigentuemerMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eigentuemer")
@RequiredArgsConstructor
public class EigentuemerController {
    private final MQTTPublisher publisher;
    private final EigentuemerRepo eigentuemerRepo;
    private final AdressenRepo adressenRepo;

    @PutMapping("/hinzufuegen")
    public void testMqttEigentuemerHinzufuegen(){
        Adresse adresse = adressenRepo.save(new Adresse("Mont-Cenis-Platz", 1, "", 44555, "Herne", "Sodingen", null));
        Eigentuemer eigentuemer = new Eigentuemer(null, "Max", "Mustermann", adresse);
        publisher.publishMessage(new EigentuemerMessage(Action.ADD, eigentuemerRepo.save(eigentuemer)));
    }

    @PutMapping("/update")
    public void testMqttEigentuemerUpdaten(){
        String updatedVorname = "updatedVorname";
        String updatedNachname = "updatedNachname";
        Long id = 1L;

        Eigentuemer eigentuemer = eigentuemerRepo.findById(id).map(eig -> {
            eig.setVorname(updatedVorname);
            eig.setNachname(updatedNachname);
            return eigentuemerRepo.save(eig);
        }).orElseThrow(() -> new EntityNotFoundException("Eigentuemer mit ID " + id + " nicht gefunden"));

        publisher.publishMessage(new EigentuemerMessage(Action.UPDATE, eigentuemer));
    }

    @DeleteMapping("/delete")
    public void testMqttEigentuemerLoeschen(){
        Eigentuemer eigentuemer = eigentuemerRepo.findById(1L).orElseThrow();
        eigentuemerRepo.deleteById(1L);
        publisher.publishMessage(new EigentuemerMessage(Action.DELETE, eigentuemer));
    }
}
