package de.fhdortmund.schrottverwaltung.immobilie.service;

import de.fhdortmund.schrottverwaltung.bewertung.dto.BewertungDTO;
import de.fhdortmund.schrottverwaltung.bewertung.entity.Bewertung;
import de.fhdortmund.schrottverwaltung.bewertung.mapper.BewertungMapper;
import de.fhdortmund.schrottverwaltung.eigentuemer.Repository.EigentuemerRepo;
import de.fhdortmund.schrottverwaltung.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottverwaltung.immobilie.ImmoStatusEnum;
import de.fhdortmund.schrottverwaltung.immobilie.dto.AdresseDTO;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmoStatusDTO;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieReceivedDTO;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Adresse;
import de.fhdortmund.schrottverwaltung.immobilie.entity.ImmoStatus;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Koordinaten;
import de.fhdortmund.schrottverwaltung.immobilie.mapper.ImmoStatusMapper;
import de.fhdortmund.schrottverwaltung.immobilie.mapper.ImmobilienMapper;
import de.fhdortmund.schrottverwaltung.immobilie.repo.AdressenRepo;
import de.fhdortmund.schrottverwaltung.immobilie.repo.ImmobilienRepo;
import de.fhdortmund.schrottverwaltung.immobilie.repo.ImmobilienStatusRepo;
import de.fhdortmund.schrottverwaltung.immobilie.repo.KoordinatenRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ImmobilienService {
    private final ImmobilienStatusRepo immobilienStatusRepo;
    private final EigentuemerRepo eigentuemerRepo;
    private final BewertungMapper bewertungMapper;
    private final ImmobilienMapper immobilienMapper;
    private final AdressenRepo adressenRepo;
    private final KoordinatenRepo koordinatenRepo;
    private final ImmoStatusMapper immoStatusMapper;
    private final ProxyService proxyService;

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
        return proxyService.getReferenceById(id).getBild();
    }

    public Immobilie getImmobilieById(Long id) { return proxyService.getImmobilieById(id); }

    public void setArchiviert(long id, boolean status){
        Immobilie immobilie = proxyService.getImmobilieById(id);
        if(immobilie == null)
            return;
        immobilie.setArchiviert(status);
        proxyService.updateImmobilie(immobilie);
    }

    public void setStatus(long id, Long statusId) {
        Immobilie immobilie = proxyService.getImmobilieById(id);
        if(immobilie == null)
            return;
        immobilie.setAktuellerStatusId(statusId.intValue());
        proxyService.updateImmobilie(immobilie);
    }

    public void setEigentuemer(long id, Long eigentuemerId) {
        Immobilie immobilie = proxyService.getImmobilieById(id);
        Optional<Eigentuemer> maybeEigentuemer = eigentuemerRepo.findById(eigentuemerId);
        if(immobilie == null ||maybeEigentuemer.isEmpty())
            return;
        Eigentuemer eigentuemer = maybeEigentuemer.get();
        immobilie.setEigentuemer(eigentuemer);
        proxyService.updateImmobilie(immobilie);
    }

    public void addBewertung(long id, BewertungDTO bewertung) {
        Immobilie immobilie = proxyService.getImmobilieById(id);
        if(immobilie == null)
            return;
        List<Bewertung> bewertungen = immobilie.getBewertungen();
        Bewertung neueBewertung  = bewertungMapper.toBewertung(bewertung);
        bewertungen.add(neueBewertung);
        immobilie.setBewertungen(bewertungen);
        proxyService.updateImmobilie(immobilie);
    }

    @Transactional
    public void addStatus(long id, ImmoStatusDTO statusDto) {
        ImmoStatus status = immoStatusMapper.toEntity(statusDto);
        Immobilie immobilie = proxyService.getImmobilieById(id);
        if(immobilie == null)
            return;
        status = immobilienStatusRepo.save(status);
        List<ImmoStatus> stati = immobilie.getImmoStati();
        stati.add(status);
        immobilie.setImmoStati(stati);
        proxyService.updateImmobilie(immobilie);
    }

    public void setActiveStatus(Long luis, Long noel) {
        Immobilie immobilie = proxyService.getImmobilieById(noel);
        if(immobilie == null)
            return;
        immobilie.setAktuellerStatusId(luis.intValue());
        proxyService.updateImmobilie(immobilie);
    }

    /**
     * Updates an existing {@link Immobilie} based on the data provided in the given {@link ImmobilieReceivedDTO}.
     * <p>
     * If the property with the given ID exists, it will be updated with the new data.
     * If the property does not exist, a warning will be logged and no update will be performed.
     *
     * @param immobilieReceivedDTO the DTO object containing the updated property data
     */
    public void updateImmobilie(ImmobilieReceivedDTO immobilieReceivedDTO){ proxyService.updateImmobilie(mapDtoToEntity(immobilieReceivedDTO));}

    public void setAdresse(long id, AdresseDTO adress) {
        Immobilie immobilie =  proxyService.getImmobilieById(id);
        if(immobilie == null)
            return;
        Adresse adresse =immobilie.getAdresse();
        adresse.setStrasse(adress.strasse());
        adresse.setHausnummer(adress.hausnummer());
        immobilie.setAdresse(adresse);
        proxyService.updateImmobilie(immobilie);
    }

    public void setZustand(long id, String zustand) {
        Immobilie immobilie = proxyService.getImmobilieById(id);
        if(immobilie == null)
            return;
        immobilie.setZustand(zustand);
        proxyService.updateImmobilie(immobilie);
    }

    /**
     * Deletes an existing {@link Immobilie} by its ID.
     * <p>
     * If the property with the given ID exists, it will be deleted from the database.
     * If the property does not exist, a warning will be logged and no deletion will be performed.
     *
     * @param id the ID of the property to be deleted
     */
    public void deleteImmobilie(Long id){ proxyService.deleteImmobilie(id); }
}
