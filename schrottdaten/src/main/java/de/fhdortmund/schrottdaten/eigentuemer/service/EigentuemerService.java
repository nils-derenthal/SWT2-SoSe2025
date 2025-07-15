package de.fhdortmund.schrottdaten.eigentuemer.service;

import de.fhdortmund.schrottdaten.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottdaten.eigentuemer.repo.EigentuemerRepo;
import de.fhdortmund.schrottdaten.immobilie.entity.Adresse;
import de.fhdortmund.schrottdaten.immobilie.service.AdressenService;
import de.fhdortmund.schrottdaten.mqtt.MQTTPublisher;
import de.fhdortmund.schrottdaten.mqtt.messages.Action;
import de.fhdortmund.schrottdaten.mqtt.messages.EigentuemerMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EigentuemerService {
    private final MQTTPublisher publisher;
    private final EigentuemerRepo eigentuemerRepo;
    private final AdressenService adressenService;

    /**
     * Inserts an Eigentuemer with a predefined address into the system.
     * <p>
     * This method creates a sample address and a corresponding Eigentuemer,
     * saves them using the appropriate services/repositories, and then publishes an
     * {@link EigentuemerMessage} with the action {@code ADD} to notify other components
     * of the new entry.
     */
    public void dummyEinfuegen(){
        Adresse adresse = adressenService.saveAdresse(new Adresse("Mont-Cenis-Platz", 1, "", 44555, "Herne", "Sodingen", null));
        Eigentuemer eigentuemer = new Eigentuemer(null, "Max", "Mustermann", adresse);
        publisher.publishMessage(new EigentuemerMessage(Action.ADD, eigentuemerRepo.save(eigentuemer)));
    }

    /**
     * Updates the first and last name of an existing Eigentuemer with a predefined ID.
     * <p>
     * This method attempts to retrieve the Eigentuemer with ID {@code 1}. If found, it updates
     * the first name to {@code "updatedVorname"} and the last name to {@code "updatedNachname"},
     * saves the changes to the repository, and publishes an {@link EigentuemerMessage}
     * with the action {@code UPDATE}. If no property owner with the specified ID is found,
     * an {@link EntityNotFoundException} is thrown.
     * </p>
     */
    public void dummyUpdate(){
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

    /**
     * Deletes a Eigentuemer with a predefined ID and publishes a delete message.
     * <p>
     * This method retrieves the Eigentuemer with ID {@code 1}. If the entity exists,
     * it is deleted from the repository, and an {@link EigentuemerMessage} with the action
     * {@code DELETE} is published to notify other components. If no such entity is found,
     * a {@link java.util.NoSuchElementException} will be thrown.
     * </p>
     */
    public void dummyDelete(){
        Eigentuemer eigentuemer = eigentuemerRepo.findById(1L).orElseThrow();
        eigentuemerRepo.deleteById(1L);
        publisher.publishMessage(new EigentuemerMessage(Action.DELETE, eigentuemer));
    }

    public Eigentuemer save(Eigentuemer eigentuemer){
        return eigentuemerRepo.save(eigentuemer);
    }
}
