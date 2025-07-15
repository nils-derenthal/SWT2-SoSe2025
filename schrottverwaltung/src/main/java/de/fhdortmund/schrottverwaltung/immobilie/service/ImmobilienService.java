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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ImmobilienService {
    private final ImmobilienMapper immobilienMapper;
    private final AdressenRepo adressenRepo;
    private final KoordinatenRepo koordinatenRepo;
    private final EigentuemerRepo eigentuemerRepo;
    private final ProxyService proxyService;
    private final ImmobilienRepo immobilienRepo;

    public void saveImmobilie(ImmobilieReceivedDTO immobilie){ proxyService.saveImmobilie(mapDtoToEntity(immobilie)); }

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
            if (!adressenRepo.existsById(immobilieReceivedDTO.eigentuemer().getAnschrift().getId())) {
                adressenRepo.save(immobilie.getEigentuemer().getAnschrift());
            }
            eigentuemerRepo.save(immobilie.getEigentuemer());
        }
        return immobilie;
    }


     /**
      * Retrieves a list of Immobilien based on search criteria and status filter.
      *
      * <p>This method filters Immobilien based on the following combinations:
      * <ul>
      *   <li>If both search term and status filter are provided, returns Immobilien matching both criteria</li>
      *   <li>If only status filter is provided, returns all Immobilien with that status</li>
      *   <li>If only search term is provided, returns Immobilien matching the search term</li>
      *   <li>If no criteria are provided, returns all Immobilien</li>
      * </ul>
      * </p>
      *
      * @param search       the search term to filter Immobilien by description
      * @param statusFilter the status enum to filter Immobilien
      * @return a list of {@link Immobilie} objects matching the search criteria
      */
    public List<Immobilie> getImmobilienBy(String search, ImmoStatusEnum statusFilter) {
            if (statusFilter != null && !search.isEmpty() && !statusFilter.toString().isEmpty()) {
                return proxyService.getAllByBezeichnungAndStatus(search, statusFilter);
            }
            if (statusFilter != null && !statusFilter.toString().isEmpty()) {
                return proxyService.getAllByStatus(statusFilter);
            }
            if (!search.isEmpty()) {
                return proxyService.getAllByBezeichnung(search);
            }
            return proxyService.findAll();
    }

    public List<Immobilie> getImmobilien() { return proxyService.findAll(); }

    public List<Immobilie> getArchivedImmobilien(){ return proxyService.findAllByArchiviert();}

    public byte[] getImmobilieImage(long id) {
        return immobilienRepo.getReferenceById(id).getBild();
    }

    public Immobilie getImmobilieById(Long id) { return proxyService.getImmobilieById(id); }

    public void updateImmobilie(ImmobilieReceivedDTO immobilieReceivedDTO){ proxyService.updateImmobilie(mapDtoToEntity(immobilieReceivedDTO));}

    public void deleteImmobilie(Long id){ proxyService.deleteImmobilie(id); }
}
