package de.fhdortmund.schrottverwaltung.immobilie.service;

import de.fhdortmund.schrottverwaltung.immobilie.ImmoStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImmoStatusService {

    public ImmoStatusEnum[] getAllImmoStatuses() {
        return ImmoStatusEnum.values();
    }
}
