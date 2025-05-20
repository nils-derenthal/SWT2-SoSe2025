package de.fhdortmund.schrottverwaltung.mitarbeiter.Repository

import de.fhdortmund.schrottverwaltung.mitarbeiter.Fachbereich
import org.springframework.data.repository.CrudRepository

interface FachbereichRepository : CrudRepository<Fachbereich, Long> {
}