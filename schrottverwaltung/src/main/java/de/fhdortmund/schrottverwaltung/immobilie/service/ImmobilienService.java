package de.fhdortmund.schrottverwaltung.immobilie.service;

import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import de.fhdortmund.schrottverwaltung.immobilie.repo.ImmobilienRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ImmobilienService {
    private final ImmobilienRepo immobilienRepo;

    public List<Immobilie> getImmobilienBy(String search) {
        return immobilienRepo.getAllByBezeichnung(search);
    }

    public List<Immobilie> getImmobilien() {
        return immobilienRepo.findAll();
    }
}
