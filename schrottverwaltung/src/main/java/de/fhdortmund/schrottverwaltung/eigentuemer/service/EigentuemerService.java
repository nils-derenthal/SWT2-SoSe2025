package de.fhdortmund.schrottverwaltung.eigentuemer.service;

import de.fhdortmund.schrottverwaltung.eigentuemer.dto.EigentuemerReceivedDTO;
import de.fhdortmund.schrottverwaltung.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Adresse;
import de.fhdortmund.schrottverwaltung.immobilie.repo.AdressenRepo;
import de.fhdortmund.schrottverwaltung.eigentuemer.Repository.EigentuemerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EigentuemerService {
    private final EigentuemerRepo eigentuemerRepo;
    private final AdressenRepo adressenRepo;

    /**
     * Speichert einen neuen {@link Eigentuemer} in der Datenbank, wenn dieser noch nicht existiert.
     *
     * <p>Die Methode prüft anhand der Herne-ID, ob der Eigentümer bereits in der Datenbank existiert.
     * Ist dies der Fall, wird eine Warnung geloggt und kein neuer Eintrag erzeugt.
     * Falls nicht, wird ebenfalls geprüft, ob die zugehörige Adresse bereits vorhanden ist (ebenfalls über Herne-ID).
     * Falls die Adresse nicht existiert, wird sie zuerst gespeichert.
     * Anschließend wird der Eigentümer mit einem Verweis auf die gespeicherte Adresse angelegt und gespeichert.
     *
     * <p>Diese Methode ist transaktional und führt alle Datenbankoperationen innerhalb einer Transaktion aus.
     *
     * @param eigentuemer das empfangene DTO-Objekt, das alle nötigen Informationen zum Eigentümer und seiner Adresse enthält;
     *                    darf nicht {@code null} sein.
     */
    @Transactional
    public void saveEigentuemer(EigentuemerReceivedDTO eigentuemer){
        if(eigentuemerRepo.existsById(eigentuemer.id())){
            log.warn("Eigentuemer with Id:{} already exists and is not created again", eigentuemer.id());
        }else{
            if(!adressenRepo.existsById(eigentuemer.anschrift().getId())) {
                adressenRepo.save(new Adresse(
                        eigentuemer.anschrift().getId(),
                        eigentuemer.anschrift().getStrasse(),
                        eigentuemer.anschrift().getHausnummer(),
                        eigentuemer.anschrift().getHausnummerZusatz(),
                        eigentuemer.anschrift().getPlz(),
                        eigentuemer.anschrift().getOrt(),
                        eigentuemer.anschrift().getStadtbezirk()
                ));
            }
            Adresse adr = adressenRepo.getReferenceById(eigentuemer.anschrift().getId());
            eigentuemerRepo.save(new Eigentuemer(
                    eigentuemer.id(),
                    eigentuemer.vorname(),
                    eigentuemer.nachname(),
                    adr,
                    ""));
            log.info("Eigentuemer with id:{}, has been saved", eigentuemer.id());
        }
    }

}
