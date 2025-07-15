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

    @PutMapping("/hinzufuegen")
    public void testMqttImmobilieHinzufuegen() throws IOException {
         Adresse immobilienAdresse = adressenRepo.save(new Adresse("Mont-Cenis-Straße", 99, "", 44627, "Herne", "Musterbezirk", null));
         Adresse eigentuemerAdresse = adressenRepo.save(new Adresse("Einbahnstraße", 5, "", 44555, "München", "Musterbezirk", null));
         Eigentuemer eigentuemer = eigentuemerRepo.save(new Eigentuemer(null, "Max", "Mustermann", eigentuemerAdresse));
         Koordinaten koordinaten = koordinatenRepo.save(new Koordinaten(7.2366656450864895, 51.541236685163334, null));

        var classloader = resourceLoader.getClassLoader();

        var resolver = new PathMatchingResourcePatternResolver(classloader);
        var resources = resolver.getResources("classpath:houses/*");

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
                 .bild(resources[(int) (Math.random() * resources.length)].getContentAsByteArray())
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
            }).orElseThrow(() -> new EntityNotFoundException("Immobilie mit ID " + id + " nicht gefunden"));

        publisher.publishMessage(new ImmobilienMessage(Action.UPDATE, immobilie));
    }

    @DeleteMapping("/delete")
    public void testMqttImmobilieLoeschen(){
        Immobilie immobilie = immobilienRepo.findById(1L).orElseThrow();
        immobilienRepo.deleteById(1L);
        publisher.publishMessage(new ImmobilienMessage(Action.DELETE, immobilie));
    }

    @PutMapping()
    public void testMqttMitarbeiterHinzufuegen() {
        var adresse = new Adresse("Musterstraße", 4, "", 44555, "Musterort", "Musterbezirk", null);
        Immobilie immo = Immobilie.builder()
                .id(null)
                .adresse(adresse)
                .bezeichnung("Musterimmobilie")
                .zustand("MusterZustand")
                .koordinaten(new Koordinaten(100.0, 100.0, null))
                .gemarkung("Mustergemarkung")
                .flur("Musterflur")
                .flurstueck("Musterflurstueck")
                .quadratMeter(5)
                .gebaeudeTyp(Gebaeudetyp.WOHNHAUS)
                .eigentumsForm(EigentumsForm.VOLLEIGENTUM)
                .eigentuemer(new Eigentuemer(null, "Max", "Mustermann", adresse))
                .bild(new byte[512])
                .build();

        publisher.publishMessage(immo);
    }


}
