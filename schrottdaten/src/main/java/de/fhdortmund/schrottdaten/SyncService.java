package de.fhdortmund.schrottdaten;

import de.fhdortmund.schrottdaten.eigentuemer.repo.EigentuemerRepo;
import de.fhdortmund.schrottdaten.immobilie.repo.ImmobilienRepo;
import de.fhdortmund.schrottdaten.mqtt.MQTTPublisher;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncService {
    private final MQTTPublisher publisher;
    private final EigentuemerRepo eigentuemerRepo;
    private final ImmobilienRepo immobilienRepo;

    /**
     * Wird nach dem Start der Anwendung automatisch ausgeführt.
     * <p>
     * Lädt alle {@code Eigentuemer} und {@code Immobilie}-Einträge aus den Datenbanken
     * und übergibt sie dem {@link MQTTPublisher} zur Veröffentlichung.
     */
    @PostConstruct
    private void syncData(){
        log.info("Starting Data sync...");
        var eigentuemer = eigentuemerRepo.findAll();
        var immobilien = immobilienRepo.findAll();

        eigentuemer.forEach(publisher::publishMessage);
        immobilien.forEach(publisher::publishMessage);

        log.info("Sync finished");
    }
}
