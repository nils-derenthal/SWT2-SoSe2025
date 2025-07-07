package de.fhdortmund.schrottverwaltung.immobilie.service;

import de.fhdortmund.schrottverwaltung.eigentuemer.Eigentuemer;
import de.fhdortmund.schrottverwaltung.immobilie.AdresseT;
import de.fhdortmund.schrottverwaltung.immobilie.Immobilie;
import de.fhdortmund.schrottverwaltung.immobilie.Koordinaten;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieReceivedDTO;
import de.fhdortmund.schrottverwaltung.immobilie.repo.AdressenRepo;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import de.fhdortmund.schrottverwaltung.immobilie.repo.ImmobilienRepo;
import de.fhdortmund.schrottverwaltung.immobilie.repo.KoordinatenRepo;
import de.fhdortmund.schrottverwaltung.eigentuemer.repo.EigentuemerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ImmobilienService {
    private final ImmobilienRepo immobilienRepo;
    private final AdressenRepo adressenRepo;
    private final KoordinatenRepo koordinatenRepo;
    private final EigentuemerRepo eigentuemerRepo;

    /**
     * Saves a new {@link Immobilie} based on the data provided in the given {@link ImmobilieReceivedDTO},
     * if it does not already exist in the database.
     * <p>
     * If a property with the given ID already exists, it will not be saved,
     * and a corresponding warning will be logged.
     *
     * @param immobilie the DTO object describing the property to be saved
     */
    public void saveImmobilie(ImmobilieReceivedDTO immobilie){
        if(immobilienRepo.existsByHerneId(immobilie.id())){
            log.warn("Immobilie with id:{} already exists and is not created again", immobilie.id());
        }else{
            immobilienRepo.save(mapDtoToEntity(immobilie));
            log.info("Immobilie with id:{}, has been saved", immobilie.id());
        }
    }

    public List<Immobilie> getImmobilienBy(String search) {
        return immobilienRepo.getAllByBezeichnung(search);
    }
    /**
     * Maps an {@link ImmobilieReceivedDTO} object to an {@link Immobilie} entity.
     *
     * This method checks whether the associated addresses, coordinates, and owners already exist in their respective repositories.
     * If they do not exist, they are created and saved. Finally, a new {@link Immobilie} entity is created and returned.
     *
     * @param immobilie The {@link ImmobilieReceivedDTO} object containing the property data.
     * @return A new {@link Immobilie} entity created from the data in the DTO.
     *
     * @throws IllegalArgumentException If a required field in the DTO is null or cannot be processed correctly.
     */
    public Immobilie mapDtoToEntity(ImmobilieReceivedDTO immobilie) {
            if(!adressenRepo.existsByHerneId(immobilie.adresse().getId())) {
                adressenRepo.save(new AdresseT(immobilie.adresse().getStrasse(),
                        immobilie.adresse().getHausnummer(),
                        immobilie.adresse().getHausnummerZusatz(),
                        immobilie.adresse().getPlz(),
                        immobilie.adresse().getOrt(),
                        immobilie.adresse().getStadtbezirk(),
                        immobilie.adresse().getId(),
                        null));
            }
            if(!koordinatenRepo.existsByHerneId(immobilie.koordinaten().getId())){
                koordinatenRepo.save(new Koordinaten(
                        immobilie.koordinaten().getXKoordinate(),
                        immobilie.koordinaten().getYKoordinate(),
                        immobilie.koordinaten().getId(),
                        null
                ));
            }
            if(!eigentuemerRepo.existsByHerneId(immobilie.eigentuemer().getId())){
                if(!adressenRepo.existsByHerneId(immobilie.adresse().getId())){
                    adressenRepo.save(new AdresseT(
                            immobilie.eigentuemer().getAnschrift().getStrasse(),
                            immobilie.eigentuemer().getAnschrift().getHausnummer(),
                            immobilie.eigentuemer().getAnschrift().getHausnummerZusatz(),
                            immobilie.eigentuemer().getAnschrift().getPlz(),
                            immobilie.eigentuemer().getAnschrift().getOrt(),
                            immobilie.eigentuemer().getAnschrift().getStadtbezirk(),
                            immobilie.eigentuemer().getAnschrift().getId(),
                            null));
                }
                eigentuemerRepo.save(new Eigentuemer(
                        null,
                        immobilie.eigentuemer().getHerneId(),
                        immobilie.eigentuemer().getVorname(),
                        immobilie.eigentuemer().getNachname(),
                        adressenRepo.getByHerneId(immobilie.eigentuemer().getAnschrift().getId()),
                        ""));
            }
        System.out.println("meep");
        System.out.println(immobilie.adresse().getId());
        System.out.println(immobilie.adresse().getHerneId());
            AdresseT adr = adressenRepo.getByHerneId(immobilie.adresse().getId());
            Koordinaten kor = koordinatenRepo.getByHerneId(immobilie.koordinaten().getId());
            Eigentuemer eig = eigentuemerRepo.getByHerneId(immobilie.eigentuemer().getId());
            return new Immobilie(
                    adr,
                    immobilie.bezeichnung(),
                    false,
                    immobilie.zustand(),
                    kor,
                    immobilie.gemarkung(),
                    immobilie.flur(),
                    immobilie.flurstueck(),
                    immobilie.quadratMeter(),
                    immobilie.gebaeudeTyp(),
                    immobilie.eigentumsForm(),
                    List.of(),
                    eig,
                    null,
                    immobilie.id(),
                    null);
}

    public List<Immobilie> getImmobilienBy(String search) {
        return immobilienRepo.getAllByBezeichnung(search);
    }

    public List<Immobilie> getImmobilien() {
        return immobilienRepo.findAll();
    }
}
