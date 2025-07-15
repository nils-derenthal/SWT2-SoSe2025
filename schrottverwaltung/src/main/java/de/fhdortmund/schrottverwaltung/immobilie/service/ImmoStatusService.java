package de.fhdortmund.schrottverwaltung.immobilie.service;

import de.fhdortmund.schrottverwaltung.immobilie.ImmoStatusEnum;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmoStatusDTO;
import de.fhdortmund.schrottverwaltung.immobilie.entity.ImmoStatus;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import de.fhdortmund.schrottverwaltung.immobilie.mapper.ImmoStatusMapper;
import de.fhdortmund.schrottverwaltung.immobilie.repo.ImmobilienRepo;
import de.fhdortmund.schrottverwaltung.immobilie.repo.ImmobilienStatusRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImmoStatusService {
    private final ImmobilienStatusRepo statusRepo;
    private final ImmoStatusMapper immoStatusMapper;
    private final ImmobilienRepo immobilienRepo;

    public ImmoStatusEnum[] getAllImmoStatuses() {
        return ImmoStatusEnum.values();
    }

    public ImmoStatus getImmoStatus(Long id) {
        return statusRepo.findImmobilienStatusById(id);
    }

    @Transactional
    public long addStatus(ImmoStatusDTO status) {
        ImmoStatus immoStatus = immoStatusMapper.toEntity(status);
        Immobilie immo = immobilienRepo.getImmobilieById(status.immobilieId());
        ImmoStatus stat = new ImmoStatus(null, immoStatus.getStatus(), immoStatus.getBeschreibung(), immo);
        stat =  statusRepo.save(stat);
        return stat.getId();
    }
}
