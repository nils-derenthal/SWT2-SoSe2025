package de.fhdortmund.schrottdaten.immobilie.service;

import de.fhdortmund.schrottdaten.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottdaten.eigentuemer.service.EigentuemerService;
import de.fhdortmund.schrottdaten.immobilie.EigentumsForm;
import de.fhdortmund.schrottdaten.immobilie.Gebaeudetyp;
import de.fhdortmund.schrottdaten.immobilie.entity.Adresse;
import de.fhdortmund.schrottdaten.immobilie.entity.Immobilie;
import de.fhdortmund.schrottdaten.immobilie.entity.Koordinaten;
import de.fhdortmund.schrottdaten.immobilie.repo.ImmobilienRepo;
import de.fhdortmund.schrottdaten.mqtt.MQTTPublisher;
import de.fhdortmund.schrottdaten.mqtt.messages.Action;
import de.fhdortmund.schrottdaten.mqtt.messages.ImmobilienMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImmobilienService {
    private final MQTTPublisher publisher;
    private final AdressenService adressenService;
    private final EigentuemerService eigentuemerService;
    private final KoordinatenService koordinatenService;
    private final ImmobilienRepo immobilienRepo;

    private final ResourceLoader resourceLoader;


    public void dummyHinzufuegen() throws IOException {
        Adresse immobilienAdresse = adressenService.saveAdresse(new Adresse("Mont-Cenis-Straße", 99, "", 44627, "Herne", "Musterbezirk", null));
        Adresse eigentuemerAdresse = adressenService.saveAdresse(new Adresse("Einbahnstraße", 5, "", 44555, "München", "Musterbezirk", null));
        Eigentuemer eigentuemer = eigentuemerService.save(new Eigentuemer(null, "Max", "Mustermann", eigentuemerAdresse));
        Koordinaten koordinaten = koordinatenService.save(new Koordinaten(7.2366656450864895, 51.541236685163334, null));

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

    public void dummyUpdate(){
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

    public void dummyDelete(){
        Immobilie immobilie = immobilienRepo.findById(1L).orElseThrow();
        immobilienRepo.deleteById(1L);
        publisher.publishMessage(new ImmobilienMessage(Action.DELETE, immobilie));
    }
}
