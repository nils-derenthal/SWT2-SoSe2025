package de.fhdortmund.schrottdaten;

import de.fhdortmund.schrottdaten.eigentuemer.repo.EigentuemerRepo;
import de.fhdortmund.schrottdaten.immobilie.repo.ImmobilienRepo;
import de.fhdortmund.schrottdaten.mqtt.MQTTPublisher;
import de.fhdortmund.schrottdaten.mqtt.messages.Action;
import de.fhdortmund.schrottdaten.mqtt.messages.EigentuemerMessage;
import de.fhdortmund.schrottdaten.mqtt.messages.ImmobilienMessage;
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
     * Automatically executed after the application starts.
     * <p>
     * Loads all {@code Eigentuemer} and {@code Immobilie} entries from the databases
     * and passes them to the {@link MQTTPublisher} for publishing.
     */
    @PostConstruct
    private void syncData() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            log.error("Error while waiting before publishing");
            throw new RuntimeException(e);
        }

        log.info("Starting Data sync...");
        var eigentuemer = eigentuemerRepo.findAll();
        var immobilien = immobilienRepo.findAll();

        eigentuemer.forEach(e -> publisher.publishMessage(new EigentuemerMessage(Action.ADD, e)));
        immobilien.forEach(i -> publisher.publishMessage(new ImmobilienMessage(Action.ADD, i)));

        log.info("Sync finished");
    }
}
