package de.fhdortmund.schrottverwaltung.immobilie.controller;

import de.fhdortmund.schrottverwaltung.immobilie.ImmoStatusEnum;
import de.fhdortmund.schrottverwaltung.bewertung.dto.BewertungDTO;
import de.fhdortmund.schrottverwaltung.immobilie.dto.AdresseDTO;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmoInfoDTO;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmoStatusDTO;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieDTO;
import de.fhdortmund.schrottverwaltung.immobilie.mapper.ImmobilienMapper;
import de.fhdortmund.schrottverwaltung.immobilie.service.ImmobilienService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/immobilien")
@RequiredArgsConstructor
@Slf4j
public class ImmobilienController {
    private final ImmobilienService immobilienService;
    private final ImmobilienMapper immobilienMapper;

    @GetMapping
    public List<ImmobilieDTO> getImmobilienBy(@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "") ImmoStatusEnum statusFilter) {
        return immobilienMapper.toimmobilieDTO(immobilienService.getImmobilienBy(search, statusFilter));
    }

    @PostMapping("/{id}/archive")
    private void archive(@PathVariable long id) {
        immobilienService.setArchiviert(id, true);
    }

    @PostMapping("/{id}/unarchive")
    private void unArchive(@PathVariable long id) {
        immobilienService.setArchiviert(id, false);
    }

    @PostMapping("/{id}/bild")
    private void setBild(@PathVariable long id, @RequestBody String bild) {
        immobilienService.setBild(id, bild);
    }

    @PostMapping("/{id}/status/{statusId}")
    private void setStatus(@PathVariable long id, @PathVariable Long statusId) {
        immobilienService.setStatus(id, statusId);
    }

    @PostMapping("/{id}/info")
    private void setInfo(@PathVariable long id, @RequestBody ImmoInfoDTO info) {
        immobilienService.setInfo(id, info);
    }

    @PostMapping("/{id}/eigentuemer/{eigentuemerId}")
    private void setEigentuemer(@PathVariable long id, @PathVariable Long eigentuemerId){
        immobilienService.setEigentuemer(id, eigentuemerId);
    }

    @PostMapping("/{id}/bewertung")
    private void addBewertung(@PathVariable long id, @RequestBody BewertungDTO bewertung) {
        immobilienService.addBewertung(id, bewertung);
    }

    @GetMapping("/all")
    public List<ImmobilieDTO> getAllImmobilien() {
        return immobilienMapper.toimmobilieDTO(immobilienService.getImmobilien());
    }

    @GetMapping(value = "/bild/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImmobilienImage(@PathVariable long id) {
        return immobilienService.getImmobilieImage(id);
    }

    @GetMapping("/{id}")
    public ImmobilieDTO getImmobilieById(@PathVariable Long id) {
        return immobilienMapper.toDto(immobilienService.getImmobilieById(id));
    }

    @GetMapping("/archived")
    public List<ImmobilieDTO> getArchivedImmobilien(){
        return immobilienMapper.toimmobilieDTO(immobilienService.getArchivedImmobilien());
    }

    @PostMapping("/{id}/addStatus")
    public void addStatus(@PathVariable long id,@RequestBody ImmoStatusDTO status) {
        immobilienService.addStatus(id, status);
    }

    @PostMapping("/{id}/status/aktiv/{status}")
    public void activeStatus(@PathVariable long status, @PathVariable long id) {
        immobilienService.setActiveStatus(id, status);
    }

    @PostMapping("/{id}/adress")
    public void setAdress(@PathVariable long id, @RequestBody AdresseDTO adress) {
        immobilienService.setAdresse(id,adress);
    }

    @PostMapping("/{id}/zustand")
    public void setZustand(@PathVariable long id, @RequestBody String zustand) {
        immobilienService.setZustand(id,zustand);
    }
}
