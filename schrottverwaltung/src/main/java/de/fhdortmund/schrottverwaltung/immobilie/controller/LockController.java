package de.fhdortmund.schrottverwaltung.immobilie.controller;

import de.fhdortmund.schrottverwaltung.immobilie.service.LockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locks")
@AllArgsConstructor
public class LockController {
    private final LockService lockService;

    @PostMapping("/{id}")
    public boolean lock(@PathVariable Long id, @RequestParam String username) {
        return lockService.tryLock(id, username);
    }

    @DeleteMapping("/{id}")
    public void unlock(@PathVariable Long id, @RequestParam String username) {
        lockService.unlock(id, username);
    }

    @GetMapping("/{id}")
    public String getLock(@PathVariable Long id) {
        return lockService.getLockHolder(id);
    }
}
