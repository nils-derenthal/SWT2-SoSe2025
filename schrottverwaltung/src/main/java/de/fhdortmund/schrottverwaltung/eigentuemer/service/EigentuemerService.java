package de.fhdortmund.schrottverwaltung.eigentuemer.service;

import de.fhdortmund.schrottverwaltung.eigentuemer.Eigentuemer;
import de.fhdortmund.schrottverwaltung.eigentuemer.dto.EigentuemerReceivedDTO;
import de.fhdortmund.schrottverwaltung.eigentuemer.repo.EigentuemerRepo;
import de.fhdortmund.schrottverwaltung.immobilie.AdresseT;
import de.fhdortmund.schrottverwaltung.immobilie.repo.AdressenRepo;
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
        if(eigentuemerRepo.existsByHerneId(eigentuemer.id())){
            log.warn("Eigentuemer with HerneId:{} already exists and is not created again", eigentuemer.id());
        }else{
            if(!adressenRepo.existsByHerneId(eigentuemer.anschrift().getId())) {
                adressenRepo.save(new AdresseT(eigentuemer.anschrift().getStrasse(),
                        eigentuemer.anschrift().getHausnummer(),
                        eigentuemer.anschrift().getHausnummerZusatz(),
                        eigentuemer.anschrift().getPlz(),
                        eigentuemer.anschrift().getOrt(),
                        eigentuemer.anschrift().getStadtbezirk(),
                        eigentuemer.anschrift().getId(),
                        null));
            }
            AdresseT adr = adressenRepo.getByHerneId(eigentuemer.anschrift().getId());
            eigentuemerRepo.save(new Eigentuemer(
                    null,
                    eigentuemer.id(),
                    eigentuemer.vorname(),
                    eigentuemer.nachname(),
                    adr,
                    ""));
            log.info("Eigentuemer with id:{}, has been saved", eigentuemer.id());
        }
    }

}
