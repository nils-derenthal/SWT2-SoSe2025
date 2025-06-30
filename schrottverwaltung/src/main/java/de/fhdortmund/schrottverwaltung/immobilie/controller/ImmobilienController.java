package de.fhdortmund.schrottverwaltung.immobilie.controller;

import de.fhdortmund.schrottverwaltung.immobilie.DTO.ImmobilienDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/immobilien")
public class ImmobilienController {

    @GetMapping("/allArchived")
    public List<ImmobilienDTO> getArchivedImmobilien(){
        return
    }
}
