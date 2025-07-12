package de.fhdortmund.schrottverwaltung.immobilie.service;

import de.fhdortmund.schrottverwaltung.immobilie.ImmoStatusEnum;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import de.fhdortmund.schrottverwaltung.immobilie.repo.ImmobilienRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProxyService {
    private final ImmobilienRepo immobilienRepo;


    public void saveImmobilie(Immobilie immobilie){
        if(immobilienRepo.existsById(immobilie.getId())){
            log.warn("Immobilie with id:{} already exists and is not created again", immobilie.getId());
        }else{
            immobilienRepo.save(immobilie);
            log.info("Immobilie with id:{}, has been saved", immobilie.getId());
        }
    }

    public List<Immobilie> getAllByBezeichnungAndStatus(String search, ImmoStatusEnum statusFilter){
        log.info("Getting Immobilien by Bezeichnung: {} and Status: {}", search, statusFilter);
        var immobilien = immobilienRepo.getAllByBezeichnungAndStatus(search, statusFilter);
        log.info("Found {} Immobilien by Bezeichnung: {} and Status: {}", immobilien.size(), search, statusFilter);
        return immobilien;
    }

    public List<Immobilie> getAllByStatus(ImmoStatusEnum statusFilter){
        log.info("Getting Immobilien by Status: {}", statusFilter);
        var immobilien = immobilienRepo.getAllByStatus(statusFilter);
        log.info("Found {} Immobilien by Status: {}", immobilien.size(), statusFilter);
        return immobilien;
    }

    public List<Immobilie> getAllByBezeichnung(String search){
        log.info("Getting Immobilien by Bezeichnung: {}", search);
        var immobilien = immobilienRepo.getAllByBezeichnung(search);
        log.info("Found {} Immobilien by Bezeichnung: {}", immobilien.size(), search);
        return immobilien;
    }

    public List<Immobilie> findAll(){
        log.info("Getting all Immobilien");
        var immobilien = immobilienRepo.findAll();
        log.info("Found {} Immobilien", immobilien.size());
        return immobilien;
    }

    public List<Immobilie> findAllByArchiviert(){
        log.info("Getting all archived Immobilien");
        var immobilien = immobilienRepo.findAllByArchiviert(true);
        log.info("Found {} archived Immobilien", immobilien.size());
        return immobilien;
    }

    public Immobilie getImmobilieById(Long id){
        log.info("Getting Immobilie by id: {}", id);
        var immobilie = immobilienRepo.getImmobilieById(id);
        if(immobilie == null) log.error("No Immobilie with id: {} could be found", id);
        else log.info("Found Immobilie by id: {}", id);
        return immobilie;
    }

    public void updateImmobilie(Immobilie immobilie){
        if(immobilienRepo.existsById(immobilie.getId())){
            immobilienRepo.save(immobilie);
            log.info("Immobilie with id:{} has been updated", immobilie.getId());
        }else{
            log.warn("Immobilie with Id:{} does not exist and cannot be updated", immobilie.getId());
        }
    }

    public void deleteImmobilie(Long id){
        if(immobilienRepo.existsById(id)){
            immobilienRepo.deleteById(id);
            log.info("Immobilie with id:{} has been deleted", id);
        }else{
            log.warn("Immobilie with Id:{} does not exist and cannot be deleted", id);
        }
    }
}
