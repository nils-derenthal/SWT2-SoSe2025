package de.fhdortmund.schrottverwaltung.mitarbeiter.Repository

import de.fhdortmund.schrottverwaltung.mitarbeiter.Mitarbeiter
import org.springframework.data.repository.CrudRepository

interface MitarbeiterRepository : CrudRepository<Mitarbeiter, Long> {
}