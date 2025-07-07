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
    public void testMqttMitarbeiterHinzufuegen(){
         Immobilie immo = new Immobilie(new AdresseT("Musterstraße", 4, "", 44555, "Musterort", "Musterbezirk", null),
                            "Musterimmobilie",
                            "MusterZustand",
                            new Koordinaten(100.0, 100.0, null),
                            "Mustergemarkung",
                            "Musterflur",
                            "Musterflurstueck",
                            5,
                            Gebaeudetyp.WOHNHAUS,
                            EigentumsForm.VOLLEIGENTUM,
                            new Eigentuemer(null, "Max", "Mustermann", new AdresseT("Musterstraße", 4, "", 44555, "Musterort", "Musterbezirk", null)),
                 "",
                 null);

         publisher.publishMessage(immo);
    }


}
