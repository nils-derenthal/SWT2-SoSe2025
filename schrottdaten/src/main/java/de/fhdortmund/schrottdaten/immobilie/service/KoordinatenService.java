package de.fhdortmund.schrottdaten.immobilie.service;

import de.fhdortmund.schrottdaten.immobilie.entity.Koordinaten;
import de.fhdortmund.schrottdaten.immobilie.repo.KoordinatenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KoordinatenService {
    private final KoordinatenRepo koordinatenRepo;

    public Koordinaten save(Koordinaten koordinaten){
        return koordinatenRepo.save(koordinaten);
    }
}
