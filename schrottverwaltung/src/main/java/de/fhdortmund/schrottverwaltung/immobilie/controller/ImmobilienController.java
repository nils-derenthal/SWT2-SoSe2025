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
        System.out.println("IN ENDPOINT");
        var a = immobilienService.getImmobilienBy(search);
        var aDTO = immobilienMapper.toimmobilieDTO(a);
        System.out.println(a.getFirst().getKoordinaten().getXKoordinate());
        System.out.println(aDTO.getFirst().koordinaten().xKoordinate());
        return aDTO;
    }

    @GetMapping("/all")
    public List<ImmobilieDTO> getAllImmobilien() {
        return immobilienMapper.toimmobilieDTO(immobilienService.getImmobilien());
    }
}
