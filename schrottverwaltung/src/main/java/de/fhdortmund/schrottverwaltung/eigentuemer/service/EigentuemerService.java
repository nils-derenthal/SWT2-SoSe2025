package de.fhdortmund.schrottverwaltung.eigentuemer.service;

import de.fhdortmund.schrottverwaltung.eigentuemer.dto.EigentuemerReceivedDTO;
import de.fhdortmund.schrottverwaltung.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottverwaltung.eigentuemer.mapper.EigentuemerMapper;
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
    private final EigentuemerMapper eigentuemerMapper;
    private final EigentuemerRepo eigentuemerRepo;
    private final AdressenRepo adressenRepo;

    /**
     * Saves a new {@link Eigentuemer} in the database if it does not already exist.
     *
     * <p>The method checks whether the owner already exists in the database based on the Herne ID.
     * If this is the case, a warning is logged and no new entry is created.
     * If not, it also checks whether the associated address already exists (also based on the Herne ID).
     * If the address does not exist, it is saved first.
     * The owner is then created with a reference to the saved address and persisted.
     *
     * <p>This method is transactional and performs all database operations within a single transaction.
     *
     * @param eigentuemerReceivedDTO the received DTO object containing all necessary information
     *                               about the owner and their address; must not be {@code null}.
     */
    @Transactional
    public void saveEigentuemer(EigentuemerReceivedDTO eigentuemerReceivedDTO){
        Eigentuemer eigentuemer = eigentuemerMapper.toEntity(eigentuemerReceivedDTO);
        if(eigentuemerRepo.existsById(eigentuemerReceivedDTO.id())){
            log.warn("Eigentuemer with Id:{} already exists and is not created again", eigentuemerReceivedDTO.id());
        }else{
            if(!adressenRepo.existsById(eigentuemerReceivedDTO.anschrift().getId())) {
                adressenRepo.save(eigentuemer.getAnschrift());
            }
            eigentuemerRepo.save(eigentuemer);
            log.info("Eigentuemer with id:{}, has been saved", eigentuemerReceivedDTO.id());
        }
    }

    /**
     * Updates an existing {@link Eigentuemer} based on the data provided in the given {@link EigentuemerReceivedDTO}.
     * <p>
     * If the owner with the given ID exists, it will be updated with the new data.
     * If the owner does not exist, a warning will be logged and no update will be performed.
     *
     * @param eigentuemerReceivedDTO the DTO object containing the updated owner data
     */
    @Transactional
    public void updateEigentuemer(EigentuemerReceivedDTO eigentuemerReceivedDTO){
        if(eigentuemerRepo.existsById(eigentuemerReceivedDTO.id())){
            Eigentuemer eigentuemer = eigentuemerMapper.toEntity(eigentuemerReceivedDTO);
            eigentuemerRepo.save(eigentuemer);
            log.info("Eigentuemer with id:{} has been updated", eigentuemerReceivedDTO.id());
        }else{
            log.warn("Eigentuemer with Id:{} does not exist and cannot be updated", eigentuemerReceivedDTO.id());
        }
    }

    /**
     * Deletes an existing {@link Eigentuemer} by its ID.
     * <p>
     * If the owner with the given ID exists, it will be deleted from the database.
     * If the owner does not exist, a warning will be logged and no deletion will be performed.
     *
     * @param id the ID of the owner to be deleted
     */
    @Transactional
    public void deleteEigentuemer(Long id){
        if(eigentuemerRepo.existsById(id)){
            eigentuemerRepo.deleteById(id);
            log.info("Eigentuemer with id:{} has been deleted", id);
        }else{
            log.warn("Eigentuemer with Id:{} does not exist and cannot be deleted", id);
        }
    }

}
