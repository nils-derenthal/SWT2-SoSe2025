package de.fhdortmund.schrottdaten.immobilie;

import de.fhdortmund.schrottdaten.eigentuemer.Eigentuemer;
import de.fhdortmund.schrottdaten.mqtt.MQTTPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/immobilien")
@RequiredArgsConstructor
public class ImmobilienController {
    private final MQTTPublisher publisher;

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
                .bild("")
                .build();

        publisher.publishMessage(immo);
    }
}
