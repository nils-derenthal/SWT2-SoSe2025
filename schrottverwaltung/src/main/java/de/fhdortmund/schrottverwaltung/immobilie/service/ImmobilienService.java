package de.fhdortmund.schrottverwaltung.immobilie.service;
import de.fhdortmund.schrottverwaltung.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottverwaltung.immobilie.ImmoStatusEnum;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieReceivedDTO;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Adresse;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Koordinaten;
import de.fhdortmund.schrottverwaltung.immobilie.mapper.ImmobilienMapper;
import de.fhdortmund.schrottverwaltung.immobilie.repo.AdressenRepo;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import de.fhdortmund.schrottverwaltung.immobilie.repo.ImmobilienRepo;
import de.fhdortmund.schrottverwaltung.immobilie.repo.KoordinatenRepo;
import de.fhdortmund.schrottverwaltung.eigentuemer.Repository.EigentuemerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ImmobilienService {
    private final ImmobilienMapper immobilienMapper;
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
        if(immobilienRepo.existsById(immobilie.id())){
            log.warn("Immobilie with id:{} already exists and is not created again", immobilie.id());
        }else{
            immobilienRepo.save(mapDtoToEntity(immobilie));
            log.info("Immobilie with id:{}, has been saved", immobilie.id());
        }
    }

    /**
     * Maps an {@link ImmobilieReceivedDTO} to an {@link Immobilie} entity using the {@code immobilienMapper}.
     *
     * <p>This method ensures that referenced nested entities such as {@link Adresse}, {@link Koordinaten},
     * and {@link Eigentuemer} are persisted if they do not already exist in the database.</p>
     *
     * <p>Specifically:
     * <ul>
     *   <li>If the address referenced in the DTO does not exist in the {@code adressenRepo}, it is saved.</li>
     *   <li>If the coordinates do not exist in the {@code koordinatenRepo}, they are saved.</li>
     *   <li>If the owner does not exist in the {@code eigentuemerRepo}, the owner and their address are saved
     *       (if the address is also not already present).</li>
     * </ul>
     * </p>
     *
     * @param immobilieReceivedDTO the DTO containing the data to be mapped to an entity
     * @return a mapped {@link Immobilie} entity, ready for further processing or persistence
     */
    public Immobilie mapDtoToEntity(ImmobilieReceivedDTO immobilieReceivedDTO) {
        Immobilie immobilie = immobilienMapper.toEntity(immobilieReceivedDTO);
        if (!adressenRepo.existsById(immobilieReceivedDTO.adresse().getId())) {
            adressenRepo.save(immobilie.getAdresse());
        }
        if (!koordinatenRepo.existsById(immobilieReceivedDTO.koordinaten().getId())) {
            koordinatenRepo.save(immobilie.getKoordinaten());
        }
        if (!eigentuemerRepo.existsById(immobilieReceivedDTO.eigentuemer().getId())) {
            if (!adressenRepo.existsById(immobilieReceivedDTO.adresse().getId())) {
                adressenRepo.save(immobilie.getEigentuemer().getAnschrift());
            }
            eigentuemerRepo.save(immobilie.getEigentuemer());
        }
        return immobilie;
    }


    public List<Immobilie> getImmobilienBy(String search, ImmoStatusEnum statusFilter) {
        if (statusFilter != null && !search.isEmpty() && !statusFilter.toString().isEmpty()) {
            return immobilienRepo.getAllByBezeichnungAndStatus(search, statusFilter);
        }
        if (statusFilter != null && !statusFilter.toString().isEmpty()) {
            return immobilienRepo.getAllByStatus(statusFilter);
        }
        if (!search.isEmpty()) {
            return immobilienRepo.getAllByBezeichnung(search);
        }
        return immobilienRepo.findAll();
    }

    public List<Immobilie> getImmobilien() {
        return immobilienRepo.findAll();
    }

    public List<Immobilie> getArchivedImmobilien(){ return immobilienRepo.findAllByArchiviert(true);}

    public Immobilie getImmobilieById(Long id) { return immobilienRepo.getImmobilieById(id); }
}
