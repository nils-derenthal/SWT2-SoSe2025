package de.fhdortmund.schrottverwaltung.immobilie.controller;

import de.fhdortmund.schrottverwaltung.immobilie.ImmoStatusEnum;
import de.fhdortmund.schrottverwaltung.immobilie.service.ImmoStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/immobilie/status")
public class ImmoStatusController {
    private final ImmoStatusService immoStatusService;

    @GetMapping
    public ImmoStatusEnum[] getStati() {
        return immoStatusService.getAllImmoStatuses();
    }

    @GetMapping("{immoId}")
    public ImmoStatus getImmoStatus(@PathVariable Long immoId) {
        return immoStatusService.getImmoStatus(immoId);
    }
}
