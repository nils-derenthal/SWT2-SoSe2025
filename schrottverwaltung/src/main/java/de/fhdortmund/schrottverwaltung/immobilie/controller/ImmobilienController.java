package de.fhdortmund.schrottverwaltung.immobilie.controller;

import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieDTO;
import de.fhdortmund.schrottverwaltung.immobilie.mapper.ImmobilienMapper;
import de.fhdortmund.schrottverwaltung.immobilie.service.ImmobilienService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/immobilien")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ImmobilienController {
    private final ImmobilienService immobilienService;
    private final ImmobilienMapper immobilienMapper;

    @GetMapping
    public List<ImmobilieDTO> getImmobilienBy(@RequestParam(defaultValue = "") String search) {
        return immobilienMapper.toimmobilieDTO(immobilienService.getImmobilienBy(search));
    }
}
