package de.fhdortmund.schrottverwaltung.bewertung.Repository

import de.fhdortmund.schrottverwaltung.bewertung.Kriterium
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class TestController internal constructor(var bewertungsRepository: BewertungsRepository, var kriteriumsRepository: KriteriumsRepository) {

    @GetMapping("/bewertung")
    fun test(): String {
        kriteriumsRepository.save(Kriterium("ad",2))
        return "test"
    }
}