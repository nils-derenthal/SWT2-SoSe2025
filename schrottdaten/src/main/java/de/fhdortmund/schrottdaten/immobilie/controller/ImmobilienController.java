package de.fhdortmund.schrottdaten.immobilie.controller;

import de.fhdortmund.schrottdaten.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottdaten.eigentuemer.repo.EigentuemerRepo;
import de.fhdortmund.schrottdaten.immobilie.EigentumsForm;
import de.fhdortmund.schrottdaten.immobilie.Gebaeudetyp;
import de.fhdortmund.schrottdaten.immobilie.entity.*;
import de.fhdortmund.schrottdaten.immobilie.repo.AdressenRepo;
import de.fhdortmund.schrottdaten.immobilie.repo.ImmobilienRepo;
import de.fhdortmund.schrottdaten.immobilie.repo.KoordinatenRepo;
import de.fhdortmund.schrottdaten.mqtt.MQTTPublisher;
import de.fhdortmund.schrottdaten.mqtt.messages.Action;
import de.fhdortmund.schrottdaten.mqtt.messages.ImmobilienMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/immobilien")
@RequiredArgsConstructor
public class ImmobilienController {
    private final MQTTPublisher publisher;
    private final ImmobilienRepo immobilienRepo;
    private final AdressenRepo adressenRepo;
    private final EigentuemerRepo eigentuemerRepo;
    private final KoordinatenRepo koordinatenRepo;

    @PutMapping("/hinzufuegen")
    public void testMqttImmobilieHinzufuegen(){
         Adresse immobilienAdresse = adressenRepo.save(new Adresse("Mont-Cenis-Straße", 99, "", 44627, "Herne", "Musterbezirk", null));
         Adresse eigentuemerAdresse = adressenRepo.save(new Adresse("Einbahnstraße", 5, "", 44555, "München", "Musterbezirk", null));
         Eigentuemer eigentuemer = eigentuemerRepo.save(new Eigentuemer(null, "Max", "Mustermann", eigentuemerAdresse));
         Koordinaten koordinaten = koordinatenRepo.save(new Koordinaten(7.2366656450864895, 51.541236685163334, null));

         Immobilie immo = Immobilie.builder()
                 .id(null)
                 .adresse(immobilienAdresse)
                 .bezeichnung("Musterimmobilie")
                 .zustand("MusterZustand")
                 .koordinaten(koordinaten)
                 .gemarkung("Mustergemarkung")
                 .flur("Musterflur")
                 .flurstueck("Musterflurstueck")
                 .quadratMeter(5)
                 .gebaeudeTyp(Gebaeudetyp.WOHNHAUS)
                 .eigentumsForm(EigentumsForm.VOLLEIGENTUM)
                 .eigentuemer(eigentuemer)
                 .bild("")
                 .build();
         var immobilie =  immobilienRepo.save(immo);
         publisher.publishMessage(new ImmobilienMessage(Action.ADD, immobilie));
    }

    @PutMapping("/update")
    public void testMqttImmobilieUpdaten(){
        String updatedBezeichnung = "updatedBezeichnung";
        String updatedFlur = "updatedFlur";
        Long id = 1L;

        Immobilie immobilie = immobilienRepo.findById(id).map(immo -> {
            immo.setBezeichnung(updatedBezeichnung);
            immo.setFlur(updatedFlur);
            return immobilienRepo.save(immo);
            }).orElseThrow(() -> new EntityNotFoundException("Immobilie mit ID " + id + " nicht gefunden"));;

        publisher.publishMessage(new ImmobilienMessage(Action.UPDATE, immobilie));
    }

    @DeleteMapping("/delete")
    public void testMqttImmobilieLoeschen(){
        Immobilie immobilie = immobilienRepo.findById(1L).orElseThrow();
        immobilienRepo.deleteById(1L);
        publisher.publishMessage(new ImmobilienMessage(Action.DELETE, immobilie));
    }


}
