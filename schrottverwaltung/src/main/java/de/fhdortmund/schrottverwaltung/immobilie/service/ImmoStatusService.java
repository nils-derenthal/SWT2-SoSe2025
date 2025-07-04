package de.fhdortmund.schrottverwaltung.immobilie.service;

import de.fhdortmund.schrottverwaltung.immobilie.entity.ImmoStatus;
import de.fhdortmund.schrottverwaltung.immobilie.repo.ImmobilienStatusRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImmoStatusService {
    private ImmobilienStatusRepo statusRepo;

    public ImmoStatus getImmoStatus(Long id) {
        return statusRepo.findImmobilienStatusById(id);
    }
}
