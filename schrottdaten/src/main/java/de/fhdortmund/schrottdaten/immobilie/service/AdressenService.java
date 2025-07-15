package de.fhdortmund.schrottdaten.immobilie.service;

import de.fhdortmund.schrottdaten.immobilie.entity.Adresse;
import de.fhdortmund.schrottdaten.immobilie.repo.AdressenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdressenService {
    private final AdressenRepo adressenRepo;
    public Adresse saveAdresse(Adresse adresse){
        return adressenRepo.save(adresse);
    }
}
