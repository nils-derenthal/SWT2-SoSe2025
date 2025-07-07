package de.fhdortmund.schrottverwaltung.immobilie.controller;

import de.fhdortmund.schrottverwaltung.immobilie.entity.ImmoStatus;
import de.fhdortmund.schrottverwaltung.immobilie.service.ImmoStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/immobilie/status")
public class ImmoStatusController {
    private final ImmoStatusService immoStatusService;

    @GetMapping
    public List<ImmoStatus> getImmoStatuses() {
        return immoStatusService.getAllImmoStatuses();
    }
}
